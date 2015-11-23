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
package org.eclipse.oomph.setup;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Installation Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.oomph.setup.InstallationTask#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.oomph.setup.InstallationTask#getRelativeProductFolder <em>Relative Product Folder</em>}</li>
 * </ul>
 *
 * @see org.eclipse.oomph.setup.SetupPackage#getInstallationTask()
 * @model
 * @generated
 */
public interface InstallationTask extends SetupTask
{
  /**
   * Returns the value of the '<em><b>Location</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Location</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Location</em>' attribute.
   * @see #setLocation(String)
   * @see org.eclipse.oomph.setup.SetupPackage#getInstallationTask_Location()
   * @model default="" required="true"
   *        annotation="http://www.eclipse.org/oomph/setup/Variable filter='canonical' type='STRING' label='Installation location rule' description='The rule for the absolute folder location where the product is installed' storageURI='null' explicitType='FOLDER' explicitLabel='Installation location' explicitDescription='The absolute folder location where the product is installed'"
   *        annotation="http://www.eclipse.org/oomph/setup/RuleVariable name='install.root' type='FOLDER' label='Root install folder' defaultValue='${oomph.home}' description='The root install folder where all the products are installed' storageURI='scope://'"
   *        annotation="http://www.eclipse.org/oomph/setup/RuleVariable name='installation.id' type='STRING' label='Installation folder name' defaultValue='${scope.product.name|installationID}' description='The name of the folder within the root install folder where the product is installed'"
   * @generated
   */
  String getLocation();

  /**
   * Sets the value of the '{@link org.eclipse.oomph.setup.InstallationTask#getLocation <em>Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Location</em>' attribute.
   * @see #getLocation()
   * @generated
   */
  void setLocation(String value);

  /**
   * Returns the value of the '<em><b>Relative Product Folder</b></em>' attribute.
   * The default value is <code>""</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Relative Product Folder</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Relative Product Folder</em>' attribute.
   * @see #setRelativeProductFolder(String)
   * @see org.eclipse.oomph.setup.SetupPackage#getInstallationTask_RelativeProductFolder()
   * @model default="" required="true"
   * @generated
   */
  String getRelativeProductFolder();

  /**
   * Sets the value of the '{@link org.eclipse.oomph.setup.InstallationTask#getRelativeProductFolder <em>Relative Product Folder</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Relative Product Folder</em>' attribute.
   * @see #getRelativeProductFolder()
   * @generated
   */
  void setRelativeProductFolder(String value);

} // InstallationTask
