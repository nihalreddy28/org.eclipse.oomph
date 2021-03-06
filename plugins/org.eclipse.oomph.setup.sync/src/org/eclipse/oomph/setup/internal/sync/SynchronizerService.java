/*
 * Copyright (c) 2015 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.oomph.setup.internal.sync;

import org.eclipse.oomph.preferences.util.PreferencesUtil;
import org.eclipse.oomph.setup.internal.core.util.SetupCoreUtil;
import org.eclipse.oomph.setup.internal.sync.DataProvider.NotFoundException;
import org.eclipse.oomph.setup.internal.sync.RemoteDataProvider.AuthorizationRequiredException;
import org.eclipse.oomph.util.IOUtil;
import org.eclipse.oomph.util.ObjectUtil;
import org.eclipse.oomph.util.StringUtil;

import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.StorageException;

import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Eike Stepper
 */
public final class SynchronizerService
{
  private static final String USERNAME_KEY = "username";

  private static final String PASSWORD_KEY = "password";

  private final String label;

  private final URI serviceURI;

  private final URI signupURI;

  public SynchronizerService(String label, URI serviceURI, URI signupURI)
  {
    if (serviceURI == null)
    {
      throw new IllegalArgumentException("Service URI is null");
    }

    this.label = label;
    this.serviceURI = serviceURI;
    this.signupURI = signupURI;
  }

  public String getLabel()
  {
    if (StringUtil.isEmpty(label))
    {
      return getServiceURI().toString();
    }

    return label;
  }

  public URI getServiceURI()
  {
    return serviceURI;
  }

  public URI getSignupURI()
  {
    return signupURI;
  }

  public SynchronizerCredentials getCredentials()
  {
    try
    {
      ISecurePreferences securePreferences = getPreferences();
      String username = securePreferences.get(USERNAME_KEY, null);
      String password = securePreferences.get(PASSWORD_KEY, null);

      if (username != null && password != null)
      {
        return new SynchronizerCredentials(username, password);
      }
    }
    catch (StorageException ex)
    {
      SetupSyncPlugin.INSTANCE.log(ex);
    }

    return null;
  }

  public void setCredentials(SynchronizerCredentials credentials)
  {
    try
    {
      ISecurePreferences securePreferences = getPreferences();
      if (credentials == null)
      {
        securePreferences.remove(USERNAME_KEY);
        securePreferences.remove(PASSWORD_KEY);
      }
      else
      {
        securePreferences.put(USERNAME_KEY, credentials.getUsername(), true);
        securePreferences.put(PASSWORD_KEY, credentials.getPassword(), true);
      }

      securePreferences.flush();
    }
    catch (Exception ex)
    {
      SetupSyncPlugin.INSTANCE.log(ex);
    }
  }

  public boolean validateCredentials(SynchronizerCredentials credentials) throws IOException
  {
    if (credentials == null)
    {
      return false;
    }

    Credentials usernamePasswordCredentials = new UsernamePasswordCredentials(credentials.getUsername(), credentials.getPassword());
    RemoteDataProvider remoteDataProvider = new RemoteDataProvider(serviceURI, usernamePasswordCredentials);
    Snapshot remoteSnapshot = new Snapshot(remoteDataProvider, getSyncFolder());

    File file = remoteSnapshot.getNewFile();

    try
    {
      remoteDataProvider.update(file);
    }
    catch (AuthorizationRequiredException ex)
    {
      return false;
    }
    catch (NotFoundException ex)
    {
      //$FALL-THROUGH$
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
      throw ex;
    }

    return true;
  }

  /**
   * Returns a {@link RemoteDataProvider} configured to use this synchronizer service.
   *
   * @throws AuthorizationRequiredException
   */
  public RemoteDataProvider createDataProvider(CredentialsProvider credentialsProvider) throws AuthorizationRequiredException
  {
    SynchronizerCredentials credentials = getCredentials(credentialsProvider);
    if (credentials == null)
    {
      throw new AuthorizationRequiredException(serviceURI);
    }

    Credentials usernamePasswordCredentials = new UsernamePasswordCredentials(credentials.getUsername(), credentials.getPassword());
    return new RemoteDataProvider(serviceURI, usernamePasswordCredentials);
  }

  public File getSyncFolder()
  {
    String serviceNode = getServiceNode();
    if (serviceNode == null)
    {
      return null;
    }

    return SetupSyncPlugin.INSTANCE.getUserLocation().append(serviceNode).toFile();
  }

  @Override
  public int hashCode()
  {
    return ObjectUtil.hashCode(serviceURI);
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == this)
    {
      return true;
    }

    if (obj != null && obj.getClass() == SynchronizerService.class)
    {
      SynchronizerService that = (SynchronizerService)obj;
      return ObjectUtil.equals(serviceURI, that.serviceURI);
    }

    return false;
  }

  @Override
  public String toString()
  {
    return getLabel();
  }

  private ISecurePreferences getPreferences()
  {
    String serviceNode = getServiceNode();
    if (serviceNode == null)
    {
      return null;
    }

    return PreferencesUtil.getSecurePreferences().node(SetupCoreUtil.OOMPH_NAMESPACE).node("sync").node(serviceNode);
  }

  private String getServiceNode()
  {
    URI serviceURI = getServiceURI();
    if (serviceURI == null)
    {
      return null;
    }

    return IOUtil.encodeFileName(serviceURI.toString());
  }

  private SynchronizerCredentials getCredentials(CredentialsProvider credentialsProvider)
  {
    SynchronizerCredentials credentials = getCredentials();
    if (credentials == null)
    {
      if (credentialsProvider != null)
      {
        credentials = credentialsProvider.provideCredentials(this);
        if (credentials != null)
        {
          setCredentials(credentials);
        }
      }
    }

    return credentials;
  }

  /**
   * @author Eike Stepper
   */
  public interface CredentialsProvider
  {
    public SynchronizerCredentials provideCredentials(SynchronizerService service);
  }

  /**
   * @author Eike Stepper
   */
  public static final class Registry
  {
    public static final Registry INSTANCE = new Registry();

    public static final URI ECLIPSE_SERVICE_URI = IOUtil.newURI("https://dev.eclipse.org/oomph/");

    private final Map<URI, SynchronizerService> services = new HashMap<URI, SynchronizerService>();

    private Registry()
    {
    }

    public SynchronizerService[] getServices()
    {
      synchronized (services)
      {
        return services.values().toArray(new SynchronizerService[services.size()]);
      }
    }

    public SynchronizerService getService(URI serviceURI)
    {
      return services.get(serviceURI);
    }

    public void addService(SynchronizerService service)
    {
      URI serviceURI = service.getServiceURI();

      synchronized (services)
      {
        services.put(serviceURI, service);
      }
    }

    public void removeService(SynchronizerService service)
    {
      URI serviceURI = service.getServiceURI();

      synchronized (services)
      {
        services.remove(serviceURI);
      }
    }
  }
}
