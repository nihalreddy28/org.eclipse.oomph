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
package org.eclipse.oomph.targlets.internal.ui;

import org.eclipse.oomph.p2.internal.ui.P2UIPlugin;
import org.eclipse.oomph.targlets.presentation.TargletEditorPlugin;
import org.eclipse.oomph.ui.OomphUIPlugin;

import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * @author Eike Stepper
 */
public final class TargletsUIPlugin extends OomphUIPlugin
{
  public static final TargletsUIPlugin INSTANCE = new TargletsUIPlugin();

  private static Implementation plugin;

  public TargletsUIPlugin()
  {
    super(new ResourceLocator[] { P2UIPlugin.INSTANCE, TargletEditorPlugin.INSTANCE, });
  }

  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }

  /**
   * @author Eike Stepper
   */
  public static class Implementation extends EclipseUIPlugin
  {
    public Implementation()
    {
      plugin = this;
    }
  }
}
