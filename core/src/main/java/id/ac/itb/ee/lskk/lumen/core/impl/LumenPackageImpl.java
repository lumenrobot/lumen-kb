/**
 */
package id.ac.itb.ee.lskk.lumen.core.impl;

import id.ac.itb.ee.lskk.lumen.core.LumenFactory;
import id.ac.itb.ee.lskk.lumen.core.LumenPackage;
import id.ac.itb.ee.lskk.lumen.core.LumenSysConfig;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.soluvas.commons.CommonsPackage;
import org.soluvas.socmed.SocmedPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LumenPackageImpl extends EPackageImpl implements LumenPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lumenSysConfigEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see id.ac.itb.ee.lskk.lumen.core.LumenPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LumenPackageImpl() {
		super(eNS_URI, LumenFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link LumenPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LumenPackage init() {
		if (isInited) return (LumenPackage)EPackage.Registry.INSTANCE.getEPackage(LumenPackage.eNS_URI);

		// Obtain or create and register package
		LumenPackageImpl theLumenPackage = (LumenPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LumenPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LumenPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CommonsPackage.eINSTANCE.eClass();
		SocmedPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theLumenPackage.createPackageContents();

		// Initialize created meta-data
		theLumenPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLumenPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LumenPackage.eNS_URI, theLumenPackage);
		return theLumenPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLumenSysConfig() {
		return lumenSysConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLumenSysConfig_TenantEnv() {
		return (EAttribute)lumenSysConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LumenFactory getLumenFactory() {
		return (LumenFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		lumenSysConfigEClass = createEClass(LUMEN_SYS_CONFIG);
		createEAttribute(lumenSysConfigEClass, LUMEN_SYS_CONFIG__TENANT_ENV);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CommonsPackage theCommonsPackage = (CommonsPackage)EPackage.Registry.INSTANCE.getEPackage(CommonsPackage.eNS_URI);
		SocmedPackage theSocmedPackage = (SocmedPackage)EPackage.Registry.INSTANCE.getEPackage(SocmedPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		lumenSysConfigEClass.getESuperTypes().add(theCommonsPackage.getGeneralSysConfig());
		lumenSysConfigEClass.getESuperTypes().add(theCommonsPackage.getMongoSysConfig());
		lumenSysConfigEClass.getESuperTypes().add(theSocmedPackage.getFacebookSysConfig());
		lumenSysConfigEClass.getESuperTypes().add(theSocmedPackage.getInstagramSysConfig());
		lumenSysConfigEClass.getESuperTypes().add(theSocmedPackage.getYouTubeSysConfig());
		lumenSysConfigEClass.getESuperTypes().add(theSocmedPackage.getPinterestSysConfig());
		lumenSysConfigEClass.getESuperTypes().add(theSocmedPackage.getTwitterSysConfig());

		// Initialize classes, features, and operations; add parameters
		initEClass(lumenSysConfigEClass, LumenSysConfig.class, "LumenSysConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLumenSysConfig_TenantEnv(), ecorePackage.getEString(), "tenantEnv", null, 0, 1, LumenSysConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //LumenPackageImpl
