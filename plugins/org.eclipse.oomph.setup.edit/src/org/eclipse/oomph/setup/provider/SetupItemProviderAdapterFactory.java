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
package org.eclipse.oomph.setup.provider;

import org.eclipse.oomph.base.Annotation;
import org.eclipse.oomph.base.BasePackage;
import org.eclipse.oomph.base.util.BaseSwitch;
import org.eclipse.oomph.setup.SetupFactory;
import org.eclipse.oomph.setup.SetupPackage;
import org.eclipse.oomph.setup.util.SetupAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ChildCreationExtenderManager;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SetupItemProviderAdapterFactory extends SetupAdapterFactory
    implements ComposeableAdapterFactory, IChangeNotifier, IDisposable, IChildCreationExtender
{
  /**
   * This keeps track of the root adapter factory that delegates to this adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This helps manage the child creation extenders.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ChildCreationExtenderManager childCreationExtenderManager = new ChildCreationExtenderManager(SetupEditPlugin.INSTANCE, SetupPackage.eNS_URI);

  /**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection<Object> supportedTypes = new ArrayList<Object>();

  /**
   * This constructs an instance.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetupItemProviderAdapterFactory()
  {
    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(IItemPropertySource.class);
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.Index} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IndexItemProvider indexItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.Index}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createIndexAdapter()
  {
    if (indexItemProvider == null)
    {
      indexItemProvider = new IndexItemProvider(this);
    }

    return indexItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.CatalogSelection} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CatalogSelectionItemProvider catalogSelectionItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.CatalogSelection}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createCatalogSelectionAdapter()
  {
    if (catalogSelectionItemProvider == null)
    {
      catalogSelectionItemProvider = new CatalogSelectionItemProvider(this);
    }

    return catalogSelectionItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.ProjectCatalog} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProjectCatalogItemProvider projectCatalogItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.ProjectCatalog}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createProjectCatalogAdapter()
  {
    if (projectCatalogItemProvider == null)
    {
      projectCatalogItemProvider = new ProjectCatalogItemProvider(this);
    }

    return projectCatalogItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.Project} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProjectItemProvider projectItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.Project}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createProjectAdapter()
  {
    if (projectItemProvider == null)
    {
      projectItemProvider = new ProjectItemProvider(this);
    }

    return projectItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.Stream} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StreamItemProvider streamItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.Stream}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createStreamAdapter()
  {
    if (streamItemProvider == null)
    {
      streamItemProvider = new StreamItemProvider(this);
    }

    return streamItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.Workspace} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WorkspaceItemProvider workspaceItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.Workspace}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createWorkspaceAdapter()
  {
    if (workspaceItemProvider == null)
    {
      workspaceItemProvider = new WorkspaceItemProvider(this);
    }

    return workspaceItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.Installation} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected InstallationItemProvider installationItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.Installation}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createInstallationAdapter()
  {
    if (installationItemProvider == null)
    {
      installationItemProvider = new InstallationItemProvider(this);
    }

    return installationItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.ProductCatalog} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProductCatalogItemProvider productCatalogItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.ProductCatalog}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createProductCatalogAdapter()
  {
    if (productCatalogItemProvider == null)
    {
      productCatalogItemProvider = new ProductCatalogItemProvider(this);
    }

    return productCatalogItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.Product} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProductItemProvider productItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.Product}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createProductAdapter()
  {
    if (productItemProvider == null)
    {
      productItemProvider = new ProductItemProvider(this);
    }

    return productItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.ProductVersion} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProductVersionItemProvider productVersionItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.ProductVersion}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createProductVersionAdapter()
  {
    if (productVersionItemProvider == null)
    {
      productVersionItemProvider = new ProductVersionItemProvider(this);
    }

    return productVersionItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.InstallationTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected InstallationTaskItemProvider installationTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.InstallationTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createInstallationTaskAdapter()
  {
    if (installationTaskItemProvider == null)
    {
      installationTaskItemProvider = new InstallationTaskItemProvider(this);
    }

    return installationTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.WorkspaceTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WorkspaceTaskItemProvider workspaceTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.WorkspaceTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createWorkspaceTaskAdapter()
  {
    if (workspaceTaskItemProvider == null)
    {
      workspaceTaskItemProvider = new WorkspaceTaskItemProvider(this);
    }

    return workspaceTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.CompoundTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CompoundTaskItemProvider compoundTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.CompoundTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createCompoundTaskAdapter()
  {
    if (compoundTaskItemProvider == null)
    {
      compoundTaskItemProvider = new CompoundTaskItemProvider(this);
    }

    return compoundTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.VariableTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VariableTaskItemProvider variableTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.VariableTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createVariableTaskAdapter()
  {
    if (variableTaskItemProvider == null)
    {
      variableTaskItemProvider = new VariableTaskItemProvider(this);
    }

    return variableTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.ResourceCopyTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ResourceCopyTaskItemProvider resourceCopyTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.ResourceCopyTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createResourceCopyTaskAdapter()
  {
    if (resourceCopyTaskItemProvider == null)
    {
      resourceCopyTaskItemProvider = new ResourceCopyTaskItemProvider(this);
    }

    return resourceCopyTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.TextModifyTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TextModifyTaskItemProvider textModifyTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.TextModifyTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createTextModifyTaskAdapter()
  {
    if (textModifyTaskItemProvider == null)
    {
      textModifyTaskItemProvider = new TextModifyTaskItemProvider(this);
    }

    return textModifyTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.TextModification} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TextModificationItemProvider textModificationItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.TextModification}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createTextModificationAdapter()
  {
    if (textModificationItemProvider == null)
    {
      textModificationItemProvider = new TextModificationItemProvider(this);
    }

    return textModificationItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProductToProductVersionMapEntryItemProvider productToProductVersionMapEntryItemProvider;

  /**
   * This creates an adapter for a {@link java.util.Map.Entry}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createProductToProductVersionMapEntryAdapter()
  {
    if (productToProductVersionMapEntryItemProvider == null)
    {
      productToProductVersionMapEntryItemProvider = new ProductToProductVersionMapEntryItemProvider(this);
    }

    return productToProductVersionMapEntryItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProjectToStreamMapEntryItemProvider projectToStreamMapEntryItemProvider;

  /**
   * This creates an adapter for a {@link java.util.Map.Entry}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createProjectToStreamMapEntryAdapter()
  {
    if (projectToStreamMapEntryItemProvider == null)
    {
      projectToStreamMapEntryItemProvider = new ProjectToStreamMapEntryItemProvider(this);
    }

    return projectToStreamMapEntryItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected InstallationToWorkspacesMapEntryItemProvider installationToWorkspacesMapEntryItemProvider;

  /**
   * This creates an adapter for a {@link java.util.Map.Entry}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createInstallationToWorkspacesMapEntryAdapter()
  {
    if (installationToWorkspacesMapEntryItemProvider == null)
    {
      installationToWorkspacesMapEntryItemProvider = new InstallationToWorkspacesMapEntryItemProvider(this);
    }

    return installationToWorkspacesMapEntryItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link java.util.Map.Entry} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WorkspaceToInstallationsMapEntryItemProvider workspaceToInstallationsMapEntryItemProvider;

  /**
   * This creates an adapter for a {@link java.util.Map.Entry}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createWorkspaceToInstallationsMapEntryAdapter()
  {
    if (workspaceToInstallationsMapEntryItemProvider == null)
    {
      workspaceToInstallationsMapEntryItemProvider = new WorkspaceToInstallationsMapEntryItemProvider(this);
    }

    return workspaceToInstallationsMapEntryItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.StringSubstitutionTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StringSubstitutionTaskItemProvider stringSubstitutionTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.StringSubstitutionTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createStringSubstitutionTaskAdapter()
  {
    if (stringSubstitutionTaskItemProvider == null)
    {
      stringSubstitutionTaskItemProvider = new StringSubstitutionTaskItemProvider(this);
    }

    return stringSubstitutionTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.AttributeRule} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AttributeRuleItemProvider attributeRuleItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.AttributeRule}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createAttributeRuleAdapter()
  {
    if (attributeRuleItemProvider == null)
    {
      attributeRuleItemProvider = new AttributeRuleItemProvider(this);
    }

    return attributeRuleItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.LocationCatalog} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LocationCatalogItemProvider locationCatalogItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.LocationCatalog}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createLocationCatalogAdapter()
  {
    if (locationCatalogItemProvider == null)
    {
      locationCatalogItemProvider = new LocationCatalogItemProvider(this);
    }

    return locationCatalogItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.RedirectionTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RedirectionTaskItemProvider redirectionTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.RedirectionTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createRedirectionTaskAdapter()
  {
    if (redirectionTaskItemProvider == null)
    {
      redirectionTaskItemProvider = new RedirectionTaskItemProvider(this);
    }

    return redirectionTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.VariableChoice} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VariableChoiceItemProvider variableChoiceItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.VariableChoice}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createVariableChoiceAdapter()
  {
    if (variableChoiceItemProvider == null)
    {
      variableChoiceItemProvider = new VariableChoiceItemProvider(this);
    }

    return variableChoiceItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.ResourceCreationTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ResourceCreationTaskItemProvider resourceCreationTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.ResourceCreationTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createResourceCreationTaskAdapter()
  {
    if (resourceCreationTaskItemProvider == null)
    {
      resourceCreationTaskItemProvider = new ResourceCreationTaskItemProvider(this);
    }

    return resourceCreationTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.ResourceExtractTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ResourceExtractTaskItemProvider resourceExtractTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.ResourceExtractTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createResourceExtractTaskAdapter()
  {
    if (resourceExtractTaskItemProvider == null)
    {
      resourceExtractTaskItemProvider = new ResourceExtractTaskItemProvider(this);
    }

    return resourceExtractTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.EclipseIniTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EclipseIniTaskItemProvider eclipseIniTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.EclipseIniTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createEclipseIniTaskAdapter()
  {
    if (eclipseIniTaskItemProvider == null)
    {
      eclipseIniTaskItemProvider = new EclipseIniTaskItemProvider(this);
    }

    return eclipseIniTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.User} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UserItemProvider userItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.User}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createUserAdapter()
  {
    if (userItemProvider == null)
    {
      userItemProvider = new UserItemProvider(this);
    }

    return userItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.LinkLocationTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LinkLocationTaskItemProvider linkLocationTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.LinkLocationTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createLinkLocationTaskAdapter()
  {
    if (linkLocationTaskItemProvider == null)
    {
      linkLocationTaskItemProvider = new LinkLocationTaskItemProvider(this);
    }

    return linkLocationTaskItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.eclipse.oomph.setup.PreferenceTask} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PreferenceTaskItemProvider preferenceTaskItemProvider;

  /**
   * This creates an adapter for a {@link org.eclipse.oomph.setup.PreferenceTask}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter createPreferenceTaskAdapter()
  {
    if (preferenceTaskItemProvider == null)
    {
      preferenceTaskItemProvider = new PreferenceTaskItemProvider(this);
    }

    return preferenceTaskItemProvider;
  }

  /**
   * This returns the root adapter factory that contains this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

  /**
   * This sets the composed adapter factory that contains this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
    this.parentAdapterFactory = parentAdapterFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object type)
  {
    return supportedTypes.contains(type) || super.isFactoryForType(type);
  }

  /**
   * This implementation substitutes the factory itself as the key for the adapter.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Adapter adapt(Notifier notifier, Object type)
  {
    return super.adapt(notifier, this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object adapt(Object object, Object type)
  {
    if (isFactoryForType(type))
    {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class<?>) || ((Class<?>)type).isInstance(adapter))
      {
        return adapter;
      }
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<IChildCreationExtender> getChildCreationExtenders()
  {
    return childCreationExtenderManager.getChildCreationExtenders();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain)
  {
    return childCreationExtenderManager.getNewChildDescriptors(object, editingDomain);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return childCreationExtenderManager;
  }

  /**
   * This adds a listener.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.addListener(notifyChangedListener);
  }

  /**
   * This removes a listener.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.removeListener(notifyChangedListener);
  }

  /**
   * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void fireNotifyChanged(Notification notification)
  {
    changeNotifier.fireNotifyChanged(notification);

    if (parentAdapterFactory != null)
    {
      parentAdapterFactory.fireNotifyChanged(notification);
    }
  }

  /**
   * This disposes all of the item providers created by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void dispose()
  {
    if (indexItemProvider != null)
    {
      indexItemProvider.dispose();
    }
    if (catalogSelectionItemProvider != null)
    {
      catalogSelectionItemProvider.dispose();
    }
    if (productCatalogItemProvider != null)
    {
      productCatalogItemProvider.dispose();
    }
    if (productItemProvider != null)
    {
      productItemProvider.dispose();
    }
    if (productVersionItemProvider != null)
    {
      productVersionItemProvider.dispose();
    }
    if (projectCatalogItemProvider != null)
    {
      projectCatalogItemProvider.dispose();
    }
    if (projectItemProvider != null)
    {
      projectItemProvider.dispose();
    }
    if (streamItemProvider != null)
    {
      streamItemProvider.dispose();
    }
    if (userItemProvider != null)
    {
      userItemProvider.dispose();
    }
    if (attributeRuleItemProvider != null)
    {
      attributeRuleItemProvider.dispose();
    }
    if (locationCatalogItemProvider != null)
    {
      locationCatalogItemProvider.dispose();
    }
    if (installationItemProvider != null)
    {
      installationItemProvider.dispose();
    }
    if (installationTaskItemProvider != null)
    {
      installationTaskItemProvider.dispose();
    }
    if (workspaceItemProvider != null)
    {
      workspaceItemProvider.dispose();
    }
    if (workspaceTaskItemProvider != null)
    {
      workspaceTaskItemProvider.dispose();
    }
    if (compoundTaskItemProvider != null)
    {
      compoundTaskItemProvider.dispose();
    }
    if (variableTaskItemProvider != null)
    {
      variableTaskItemProvider.dispose();
    }
    if (variableChoiceItemProvider != null)
    {
      variableChoiceItemProvider.dispose();
    }
    if (stringSubstitutionTaskItemProvider != null)
    {
      stringSubstitutionTaskItemProvider.dispose();
    }
    if (redirectionTaskItemProvider != null)
    {
      redirectionTaskItemProvider.dispose();
    }
    if (eclipseIniTaskItemProvider != null)
    {
      eclipseIniTaskItemProvider.dispose();
    }
    if (linkLocationTaskItemProvider != null)
    {
      linkLocationTaskItemProvider.dispose();
    }
    if (preferenceTaskItemProvider != null)
    {
      preferenceTaskItemProvider.dispose();
    }
    if (resourceCopyTaskItemProvider != null)
    {
      resourceCopyTaskItemProvider.dispose();
    }
    if (resourceCreationTaskItemProvider != null)
    {
      resourceCreationTaskItemProvider.dispose();
    }
    if (resourceExtractTaskItemProvider != null)
    {
      resourceExtractTaskItemProvider.dispose();
    }
    if (textModifyTaskItemProvider != null)
    {
      textModifyTaskItemProvider.dispose();
    }
    if (textModificationItemProvider != null)
    {
      textModificationItemProvider.dispose();
    }
    if (productToProductVersionMapEntryItemProvider != null)
    {
      productToProductVersionMapEntryItemProvider.dispose();
    }
    if (projectToStreamMapEntryItemProvider != null)
    {
      projectToStreamMapEntryItemProvider.dispose();
    }
    if (installationToWorkspacesMapEntryItemProvider != null)
    {
      installationToWorkspacesMapEntryItemProvider.dispose();
    }
    if (workspaceToInstallationsMapEntryItemProvider != null)
    {
      workspaceToInstallationsMapEntryItemProvider.dispose();
    }
  }

  /**
   * A child creation extender for the {@link BasePackage}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static class BaseChildCreationExtender implements IChildCreationExtender
  {
    /**
     * The switch for creating child descriptors specific to each extended class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static class CreationSwitch extends BaseSwitch<Object>
    {
      /**
       * The child descriptors being populated.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected List<Object> newChildDescriptors;

      /**
       * The domain in which to create the children.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected EditingDomain editingDomain;

      /**
       * Creates the a switch for populating child descriptors in the given domain.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      CreationSwitch(List<Object> newChildDescriptors, EditingDomain editingDomain)
      {
        this.newChildDescriptors = newChildDescriptors;
        this.editingDomain = editingDomain;
      }

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public Object caseAnnotation(Annotation object)
      {
        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createIndex()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createCatalogSelection()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createProductCatalog()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createProduct()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createProductVersion()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createProjectCatalog()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createProject()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createStream()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createUser()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createAttributeRule()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createLocationCatalog()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createInstallation()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createInstallationTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createWorkspace()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createWorkspaceTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createCompoundTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createVariableTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createVariableChoice()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createStringSubstitutionTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createRedirectionTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createEclipseIniTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createLinkLocationTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createPreferenceTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createResourceCopyTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createResourceCreationTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createResourceExtractTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createTextModifyTask()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.createTextModification()));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS,
            SetupFactory.eINSTANCE.create(SetupPackage.Literals.PRODUCT_TO_PRODUCT_VERSION_MAP_ENTRY)));

        newChildDescriptors.add(
            createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS, SetupFactory.eINSTANCE.create(SetupPackage.Literals.PROJECT_TO_STREAM_MAP_ENTRY)));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS,
            SetupFactory.eINSTANCE.create(SetupPackage.Literals.INSTALLATION_TO_WORKSPACES_MAP_ENTRY)));

        newChildDescriptors.add(createChildParameter(BasePackage.Literals.ANNOTATION__CONTENTS,
            SetupFactory.eINSTANCE.create(SetupPackage.Literals.WORKSPACE_TO_INSTALLATIONS_MAP_ENTRY)));

        return null;
      }

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected CommandParameter createChildParameter(Object feature, Object child)
      {
        return new CommandParameter(null, feature, child);
      }

    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Collection<Object> getNewChildDescriptors(Object object, EditingDomain editingDomain)
    {
      ArrayList<Object> result = new ArrayList<Object>();
      new CreationSwitch(result, editingDomain).doSwitch((EObject)object);
      return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceLocator getResourceLocator()
    {
      return SetupEditPlugin.INSTANCE;
    }
  }

}