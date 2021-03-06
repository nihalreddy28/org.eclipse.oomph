/*
 * Copyright (c) 2014 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.oomph.p2.internal.core;

import org.eclipse.emf.common.util.URI;

import org.eclipse.equinox.p2.metadata.Version;

import java.util.Map;
import java.util.Set;

/**
 * @author Eike Stepper
 */
public interface P2Index
{
  public static final int SIMPLE_REPOSITORY = 0;

  public static final int COMPOSED_REPOSITORY = 1;

  public static final P2Index INSTANCE = P2IndexImpl.INSTANCE;

  public Repository[] getRepositories();

  public Map<Repository, Set<Version>> lookupCapabilities(String namespace, String name);

  public Map<Repository, Set<Version>> generateCapabilitiesFromComposedRepositories(Map<Repository, Set<Version>> capabilitiesFromSimpleRepositories);

  /**
   * @author Eike Stepper
   */
  public interface Repository extends Comparable<Repository>
  {
    public URI getLocation();

    public int getID();

    public boolean isComposed();

    public boolean isCompressed();

    public long getTimestamp();

    public int getCapabilityCount();

    public Repository[] getChildren();

    public Repository[] getComposites();
  }
}
