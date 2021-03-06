/*
 * Copyright (c) 2014, 2015 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.oomph.p2.internal.core;

import org.eclipse.oomph.util.IORuntimeException;
import org.eclipse.oomph.util.IOUtil;
import org.eclipse.oomph.util.ReflectUtil;
import org.eclipse.oomph.util.ReflectUtil.ReflectionException;

import org.eclipse.emf.common.CommonPlugin;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.internal.p2.engine.EngineActivator;
import org.eclipse.equinox.internal.p2.engine.ProfileLock;
import org.eclipse.equinox.internal.p2.engine.SimpleProfileRegistry;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.osgi.util.NLS;

import org.osgi.framework.BundleContext;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Eike Stepper
 */
@SuppressWarnings("restriction")
public class LazyProfileRegistry extends SimpleProfileRegistry
{
  private static final String PROFILE_EXT = ".profile"; //$NON-NLS-1$

  private static final String PROFILE_GZ_EXT = ".profile.gz"; //$NON-NLS-1$

  private final Class<?> parserClass;

  private final Constructor<?> parserConstructor;

  private final Method parseMethod;

  private final Method addProfilePlaceHolderMethod;

  private final Method getProfileMapMethod;

  private final Method updateSelfProfileMethod;

  private final IProvisioningAgent provisioningAgent;

  private final File store;

  private final String self;

  private boolean updateSelfProfile;

  private final Map<String, ProfileLock> profileLocks;

  private Map<String, org.eclipse.equinox.internal.p2.engine.Profile> profileMap;

  @SuppressWarnings("unchecked")
  public LazyProfileRegistry(IProvisioningAgent provisioningAgent, File store) throws Exception
  {
    super(provisioningAgent, store);
    this.provisioningAgent = provisioningAgent;
    this.store = store;

    Field selfField = ReflectUtil.getField(SimpleProfileRegistry.class, "self");
    self = (String)ReflectUtil.getValue(selfField, this);

    Field updateSelfProfileField = ReflectUtil.getField(SimpleProfileRegistry.class, "updateSelfProfile");
    updateSelfProfile = (Boolean)ReflectUtil.getValue(updateSelfProfileField, this);

    Field profileLocksField = ReflectUtil.getField(SimpleProfileRegistry.class, "profileLocks");
    profileLocks = (Map<String, ProfileLock>)ReflectUtil.getValue(profileLocksField, this);

    try
    {
      parserClass = CommonPlugin.loadClass(EngineActivator.ID, "org.eclipse.equinox.internal.p2.engine.SimpleProfileRegistry$Parser");
      parserConstructor = ReflectUtil.getConstructor(parserClass, SimpleProfileRegistry.class, BundleContext.class, String.class);
      parseMethod = ReflectUtil.getMethod(parserClass, "parse", File.class);
      addProfilePlaceHolderMethod = ReflectUtil.getMethod(parserClass, "addProfilePlaceHolder", String.class);
      getProfileMapMethod = ReflectUtil.getMethod(parserClass, "getProfileMap");

      updateSelfProfileMethod = ReflectUtil.getMethod(SimpleProfileRegistry.class, "updateSelfProfile", Map.class);
    }
    catch (Throwable ex)
    {
      throw new Exception("The internals of p2 have changed", ex);
    }
  }

  public IProvisioningAgent getProvisioningAgent()
  {
    return provisioningAgent;
  }

  @Override
  public synchronized void resetProfiles()
  {
    profileMap = null;
  }

  @Override
  public synchronized void removeProfile(String id, long timestamp) throws ProvisionException
  {
    if (SELF.equals(id))
    {
      id = self;
    }

    org.eclipse.equinox.internal.p2.engine.Profile p = getProfileMap().get(id);
    if (p instanceof LazyProfile)
    {
      LazyProfile lazyProfile = (LazyProfile)p;
      if (lazyProfile.getDelegate(false) != null)
      {
        IProfile profile = getProfile(id);
        if (profile != null && profile.getTimestamp() == timestamp)
        {
          throw new ProvisionException("Cannot remove the current profile timestamp");
        }
      }
    }

    super.removeProfile(id, timestamp);
  }

  @Override
  public synchronized boolean containsProfile(String id)
  {
    if (SELF.equals(id))
    {
      id = self;
    }

    // Null check done after self check, because self can be null
    if (id == null)
    {
      return false;
    }

    // Check profiles to avoid restoring the profile registry
    org.eclipse.equinox.internal.p2.engine.Profile p = getProfileMap().get(id);
    if (p instanceof LazyProfile)
    {
      LazyProfile lazyProfile = (LazyProfile)p;
      if (lazyProfile.getDelegate(false) != null)
      {
        if (getProfile(id) != null)
        {
          return true;
        }
      }
    }

    return super.containsProfile(id);
  }

  @Override
  protected synchronized final Map<String, org.eclipse.equinox.internal.p2.engine.Profile> getProfileMap()
  {
    return getProfileMap(new NullProgressMonitor());
  }

  public synchronized final Map<String, org.eclipse.equinox.internal.p2.engine.Profile> getProfileMap(IProgressMonitor monitor)
  {
    if (profileMap == null)
    {
      if (store == null || !store.isDirectory())
      {
        throw new IllegalStateException(NLS.bind("Registry Directory not available: {0}.", store));
      }

      profileMap = new HashMap<String, org.eclipse.equinox.internal.p2.engine.Profile>();

      File[] profileDirectories = store.listFiles(new FileFilter()
      {
        public boolean accept(File pathname)
        {
          return pathname.getName().endsWith(PROFILE_EXT) && pathname.isDirectory();
        }
      });

      if (profileDirectories == null)
      {
        profileDirectories = new File[0];
      }

      monitor.beginTask("", profileDirectories.length);

      try
      {
        for (File profileDirectory : profileDirectories)
        {
          P2CorePlugin.checkCancelation(monitor);

          File profileFile = findLatestProfileFile(profileDirectory);
          if (profileFile == null)
          {
            monitor.subTask("Deleting " + profileDirectory);
            IOUtil.deleteBestEffort(profileFile);
          }
          else
          {
            String directoryName = profileDirectory.getName();
            String profileId = unescape(directoryName.substring(0, directoryName.lastIndexOf(PROFILE_EXT)));

            monitor.subTask("Registering profile " + profileId);

            LazyProfile profile = new LazyProfile(this, profileId, profileDirectory);
            profileMap.put(profileId, profile);
          }

          monitor.worked(1);
        }
      }
      finally
      {
        monitor.done();
      }
    }

    if (updateSelfProfile)
    {
      ReflectUtil.invokeMethod(updateSelfProfileMethod, this, profileMap);
      updateSelfProfile = false;
    }

    return profileMap;
  }

  public org.eclipse.equinox.internal.p2.engine.Profile loadProfile(String profileId, File profileDirectory)
  {
    if (store == null || !store.isDirectory())
    {
      throw new IllegalStateException(NLS.bind("Registry Directory not available: {0}.", store));
    }

    try
    {
      Object parser = ReflectUtil.newInstance(parserConstructor, this, EngineActivator.getContext(), EngineActivator.ID);

      ProfileLock lock = profileLocks.get(profileId);
      if (lock == null)
      {
        lock = new ProfileLock(this, profileDirectory);
        profileLocks.put(profileId, lock);
      }

      boolean locked = false;
      if (lock.processHoldsLock() || (locked = lock.lock()))
      {
        try
        {
          File profileFile = findLatestProfileFile(profileDirectory);
          if (profileFile != null)
          {
            try
            {
              ReflectUtil.invokeMethod(parseMethod, parser, profileFile);
            }
            catch (ReflectionException ex)
            {
              Throwable cause = ex.getCause();
              if (cause instanceof IOException)
              {
                long length = profileFile.length();
                throw new IORuntimeException("The file " + profileFile + " of length " + length + " failed to load properly", cause);
              }

              throw ex;
            }
          }
        }
        finally
        {
          if (locked)
          {
            lock.unlock();
          }
        }
      }
      else
      {
        // could not lock the profile, so add a place holder
        ReflectUtil.invokeMethod(addProfilePlaceHolderMethod, parser, profileId);
      }

      @SuppressWarnings("unchecked")
      Map<String, org.eclipse.equinox.internal.p2.engine.Profile> profileMap = //
      (Map<String, org.eclipse.equinox.internal.p2.engine.Profile>)ReflectUtil.invokeMethod(getProfileMapMethod, parser);

      return profileMap.get(profileId);
    }
    catch (RuntimeException ex)
    {
      throw ex;
    }
    catch (Exception ex)
    {
      throw new RuntimeException(ex);
    }
  }

  public static File findLatestProfileFile(File profileDirectory)
  {
    File latest = null;
    long latestTimestamp = 0;
    File[] profileFiles = profileDirectory.listFiles(new FileFilter()
    {
      public boolean accept(File pathname)
      {
        return (pathname.getName().endsWith(PROFILE_GZ_EXT) || pathname.getName().endsWith(PROFILE_EXT)) && !pathname.isDirectory();
      }
    });

    // Protect against NPE.
    if (profileFiles == null)
    {
      return null;
    }

    for (int i = 0; i < profileFiles.length; i++)
    {
      File profileFile = profileFiles[i];
      String fileName = profileFile.getName();
      try
      {
        long timestamp = Long.parseLong(fileName.substring(0, fileName.indexOf(PROFILE_EXT)));
        if (timestamp > latestTimestamp)
        {
          latestTimestamp = timestamp;
          latest = profileFile;
        }
      }
      catch (NumberFormatException e)
      {
        // Ignore.
      }
    }
    return latest;
  }
}
