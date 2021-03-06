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
package org.eclipse.oomph.setup.ui;

import org.eclipse.oomph.ui.OomphDialog;
import org.eclipse.oomph.ui.OomphUIPlugin;

import org.eclipse.swt.widgets.Shell;

/**
 * @author Eike Stepper
 */
public abstract class AbstractSetupDialog extends OomphDialog
{
  public static final String SHELL_TEXT = "Eclipse Installer";

  public AbstractSetupDialog(Shell parentShell, String title, int width, int height, OomphUIPlugin plugin, boolean helpAvailable)
  {
    super(parentShell, title, width, height, plugin, helpAvailable);
  }

  @Override
  protected String getImagePath()
  {
    return "install_wiz.png";
  }

  @Override
  protected String getShellText()
  {
    return SHELL_TEXT;
  }
}
