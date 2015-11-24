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
package org.eclipse.oomph.targlets.internal.core;

import org.eclipse.oomph.targlets.core.ITargletContainerListener;
import org.eclipse.oomph.targlets.core.TargletContainerEvent;
import org.eclipse.oomph.util.ConcurrentArray;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Eike Stepper
 */
public class TargletContainerListenerRegistry implements ITargletContainerListener.Registry
{
  public static final TargletContainerListenerRegistry INSTANCE = new TargletContainerListenerRegistry();

  private final ConcurrentArray<ITargletContainerListener> listeners = new ConcurrentArray<ITargletContainerListener>()
  {
    @Override
    protected ITargletContainerListener[] newArray(int length)
    {
      return new ITargletContainerListener[length];
    }
  };

  private final ExtensionPointHandler extensionPointHandler = new ExtensionPointHandler();

  private TargletContainerListenerRegistry()
  {
  }

  public void start() throws Exception
  {
    extensionPointHandler.start();
  }

  public void stop() throws Exception
  {
    extensionPointHandler.stop();
    listeners.clear();
  }

  public void addListener(ITargletContainerListener listener)
  {
    listeners.add(listener);
  }

  public void removeListener(ITargletContainerListener listener)
  {
    listeners.remove(listener);
  }

  public void notifyListeners(TargletContainerEvent event, IProgressMonitor monitor)
  {
    ITargletContainerListener[] targletContainerListeners = listeners.get();
    if (targletContainerListeners.length == 0)
    {
      return;
    }

    monitor.subTask("Notifying listeners of targlet container " + event.getSource().getID());
    monitor.beginTask("", targletContainerListeners.length);

    for (ITargletContainerListener listener : targletContainerListeners)
    {
      try
      {
        listener.handleTargletContainerEvent(event, new SubProgressMonitor(monitor, 1));
      }
      catch (Exception ex)
      {
        TargletsCorePlugin.INSTANCE.log(ex);
      }
    }

    monitor.done();
  }

  /**
   * @author Eike Stepper
   */
  public static class ExtensionPointHandler implements IRegistryEventListener
  {
    public static final String EXTENSION_POINT = "org.eclipse.oomph.targlets.core.targletContainerListeners";

    private final Map<IConfigurationElement, ITargletContainerListener> listeners = new HashMap<IConfigurationElement, ITargletContainerListener>();

    public ExtensionPointHandler()
    {
    }

    public void start() throws Exception
    {
      IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
      for (IConfigurationElement configurationElement : extensionRegistry.getConfigurationElementsFor(EXTENSION_POINT))
      {
        added(configurationElement);
      }

      extensionRegistry.addListener(this, EXTENSION_POINT);
    }

    public void stop() throws Exception
    {
      Platform.getExtensionRegistry().removeListener(this);

      for (ITargletContainerListener listener : listeners.values())
      {
        INSTANCE.removeListener(listener);
      }

      listeners.clear();
    }

    public void added(IExtensionPoint[] extensionPoints)
    {
      // Do nothing
    }

    public void removed(IExtensionPoint[] extensionPoints)
    {
      // Do nothing
    }

    public void added(IExtension[] extensions)
    {
      for (IExtension extension : extensions)
      {
        for (IConfigurationElement configurationElement : extension.getConfigurationElements())
        {
          added(configurationElement);
        }
      }
    }

    public void removed(IExtension[] extensions)
    {
      for (IExtension extension : extensions)
      {
        for (IConfigurationElement configurationElement : extension.getConfigurationElements())
        {
          removed(configurationElement);
        }
      }
    }

    private void added(IConfigurationElement configurationElement)
    {
      try
      {
        ITargletContainerListener listener = (ITargletContainerListener)configurationElement.createExecutableExtension("class");
        listeners.put(configurationElement, listener);
        INSTANCE.addListener(listener);
      }
      catch (Exception ex)
      {
        TargletsCorePlugin.INSTANCE.log(ex);
      }
    }

    private void removed(IConfigurationElement configurationElement)
    {
      ITargletContainerListener listener = listeners.remove(configurationElement);
      if (listener != null)
      {
        INSTANCE.removeListener(listener);
      }
    }
  }
}