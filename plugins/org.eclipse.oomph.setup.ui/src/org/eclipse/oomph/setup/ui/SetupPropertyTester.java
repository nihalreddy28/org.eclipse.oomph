/*
 * Copyright (c) 2014, 2015 Ed Merks (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ed Merks - initial API and implementation
 */
package org.eclipse.oomph.setup.ui;

import org.eclipse.oomph.internal.ui.UIPropertyTester;
import org.eclipse.oomph.ui.UIUtil;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Shell;

import org.osgi.service.prefs.Preferences;

/**
 * @author Ed Merks
 */
public class SetupPropertyTester extends PropertyTester
{
  public static final String SHOW_TOOL_BAR_CONTRIBUTIONS = "showToolBarContributions";

  public static final String SHOW_PROGRESS_IN_WIZARD = "showProgressInWizard";

  private static final Preferences PREFERENCES = SetupUIPlugin.INSTANCE.getInstancePreferences();

  private static boolean starting;

  private static IStatus performingStatus;

  private static Shell performingShell;

  private static Shell handlingShell;

  static
  {
    ((IEclipsePreferences)PREFERENCES).addPreferenceChangeListener(new IEclipsePreferences.IPreferenceChangeListener()
    {
      public void preferenceChange(final IEclipsePreferences.PreferenceChangeEvent event)
      {
        if (SHOW_TOOL_BAR_CONTRIBUTIONS.equals(event.getKey()))
        {
          UIPropertyTester.requestEvaluation("org.eclipse.oomph.setup.ui." + SHOW_TOOL_BAR_CONTRIBUTIONS, "true".equals(event.getNewValue()));
        }
      }
    });

    // This is a nasty workaround for bug 464582 (Toolbar contributions are missing after startup).
    UIUtil.timerExec(2000, new Runnable()
    {
      public void run()
      {
        UIPropertyTester.requestEvaluation("org.eclipse.oomph.setup.ui." + SHOW_TOOL_BAR_CONTRIBUTIONS, true);
      }
    });
  }

  public SetupPropertyTester()
  {
  }

  public boolean test(Object receiver, String property, Object[] args, Object expectedValue)
  {
    if ("starting".equals(property))
    {
      return testStarting(receiver, args, expectedValue);
    }

    if ("performing".equals(property))
    {
      return testPerforming(receiver, args, expectedValue);
    }

    if ("handling".equals(property))
    {
      return testHandling(receiver, args, expectedValue);
    }

    if (SHOW_TOOL_BAR_CONTRIBUTIONS.equals(property))
    {
      if (expectedValue == null)
      {
        expectedValue = Boolean.TRUE;
      }

      boolean value = PREFERENCES.getBoolean(SHOW_TOOL_BAR_CONTRIBUTIONS, false);
      return expectedValue.equals(value);
    }

    return false;
  }

  private boolean testStarting(Object receiver, Object[] args, Object expectedValue)
  {
    if (expectedValue == null)
    {
      expectedValue = Boolean.TRUE;
    }

    return expectedValue.equals(starting);
  }

  private boolean testPerforming(Object receiver, Object[] args, Object expectedValue)
  {
    if (expectedValue == null)
    {
      expectedValue = Boolean.TRUE;
    }

    return expectedValue.equals(performingShell != null);
  }

  private boolean testHandling(Object receiver, Object[] args, Object expectedValue)
  {
    if (expectedValue == null)
    {
      expectedValue = Boolean.TRUE;
    }

    return expectedValue.equals(handlingShell != null);
  }

  public static void setStarting(boolean starting)
  {
    SetupPropertyTester.starting = starting;
    UIPropertyTester.requestEvaluation("org.eclipse.oomph.setup.ui.starting", false);
  }

  public static IStatus getPerformingStatus()
  {
    return performingStatus;
  }

  public static void setPerformingStatus(IStatus performingStatus)
  {
    SetupPropertyTester.performingStatus = performingStatus;
  }

  public static Shell getPerformingShell()
  {
    return performingShell;
  }

  public static void setPerformingShell(Shell shell)
  {
    SetupPropertyTester.performingShell = shell;

    if (shell != null)
    {
      shell.addDisposeListener(new DisposeListener()
      {
        public void widgetDisposed(DisposeEvent e)
        {
          setPerformingShell(null);
        }
      });
    }

    UIPropertyTester.requestEvaluation("org.eclipse.oomph.setup.ui.performing", shell != null);
  }

  public static Shell getHandlingShell()
  {
    return handlingShell;
  }

  public static void setHandlingShell(Shell shell)
  {
    SetupPropertyTester.handlingShell = shell;

    if (shell != null)
    {
      shell.addDisposeListener(new DisposeListener()
      {
        public void widgetDisposed(DisposeEvent e)
        {
          setHandlingShell(null);
        }
      });
    }

    UIPropertyTester.requestEvaluation("org.eclipse.oomph.setup.ui.handling", false);
  }

  public static boolean isShowProgressInWizard()
  {
    return PREFERENCES.getBoolean(SHOW_PROGRESS_IN_WIZARD, false);
  }
}
