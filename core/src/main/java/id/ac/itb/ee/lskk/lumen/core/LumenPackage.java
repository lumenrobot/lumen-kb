/**
 */
package id.ac.itb.ee.lskk.lumen.core;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.soluvas.commons.CommonsPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see id.ac.itb.ee.lskk.lumen.core.LumenFactory
 * @model kind="package"
 * @generated
 */
public interface LumenPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "core";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://lskk.ee.itb.ac.id/schema/lumen.core/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "lumen";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LumenPackage eINSTANCE = id.ac.itb.ee.lskk.lumen.core.impl.LumenPackageImpl.init();

	/**
	 * The meta object id for the '{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl <em>Sys Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl
	 * @see id.ac.itb.ee.lskk.lumen.core.impl.LumenPackageImpl#getLumenSysConfig()
	 * @generated
	 */
	int LUMEN_SYS_CONFIG = 0;

	/**
	 * The feature id for the '<em><b>Expansion State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__EXPANSION_STATE = CommonsPackage.GENERAL_SYS_CONFIG__EXPANSION_STATE;

	/**
	 * The feature id for the '<em><b>Creation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__CREATION_TIME = CommonsPackage.GENERAL_SYS_CONFIG__CREATION_TIME;

	/**
	 * The feature id for the '<em><b>Modification Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__MODIFICATION_TIME = CommonsPackage.GENERAL_SYS_CONFIG__MODIFICATION_TIME;

	/**
	 * The feature id for the '<em><b>Tenant Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__TENANT_ID = CommonsPackage.GENERAL_SYS_CONFIG__TENANT_ID;

	/**
	 * The feature id for the '<em><b>Ssl Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__SSL_SUPPORTED = CommonsPackage.GENERAL_SYS_CONFIG__SSL_SUPPORTED;

	/**
	 * The feature id for the '<em><b>Mongo Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__MONGO_URI = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Facebook App Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_APP_ID = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Facebook App Secret</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_APP_SECRET = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Facebook Tenant Access Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_TENANT_ACCESS_TOKEN = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Facebook Profile Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_ID = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Facebook Profile Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_USERNAME = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Facebook Profile Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_NAME = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Facebook Tenant Page Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_ID = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Facebook Tenant Page Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_USERNAME = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Facebook Tenant Publish Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PUBLISH_ENABLED = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Facebook Explicitly Shared</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_EXPLICITLY_SHARED = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Facebook Pixel Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_PIXEL_ID = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Facebook Pixel Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__FACEBOOK_PIXEL_ENABLED = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Instagram Screen Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__INSTAGRAM_SCREEN_NAME = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>You Tube Tenant Screen Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__YOU_TUBE_TENANT_SCREEN_NAME = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Pinterest Tenant Screen Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__PINTEREST_TENANT_SCREEN_NAME = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Twitter Consumer Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__TWITTER_CONSUMER_KEY = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Twitter Consumer Secret</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__TWITTER_CONSUMER_SECRET = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Twitter Tenant Screen Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__TWITTER_TENANT_SCREEN_NAME = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Twitter Tenant Access Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Twitter Tenant Access Token Secret</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN_SECRET = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Tenant Env</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG__TENANT_ENV = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 21;

	/**
	 * The number of structural features of the '<em>Sys Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG_FEATURE_COUNT = CommonsPackage.GENERAL_SYS_CONFIG_FEATURE_COUNT + 22;

	/**
	 * The operation id for the '<em>Expand</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG___EXPAND__MAP = CommonsPackage.GENERAL_SYS_CONFIG___EXPAND__MAP;

	/**
	 * The number of operations of the '<em>Sys Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUMEN_SYS_CONFIG_OPERATION_COUNT = CommonsPackage.GENERAL_SYS_CONFIG_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link id.ac.itb.ee.lskk.lumen.core.LumenSysConfig <em>Sys Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sys Config</em>'.
	 * @see id.ac.itb.ee.lskk.lumen.core.LumenSysConfig
	 * @generated
	 */
	EClass getLumenSysConfig();

	/**
	 * Returns the meta object for the attribute '{@link id.ac.itb.ee.lskk.lumen.core.LumenSysConfig#getTenantEnv <em>Tenant Env</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tenant Env</em>'.
	 * @see id.ac.itb.ee.lskk.lumen.core.LumenSysConfig#getTenantEnv()
	 * @see #getLumenSysConfig()
	 * @generated
	 */
	EAttribute getLumenSysConfig_TenantEnv();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LumenFactory getLumenFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl <em>Sys Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl
		 * @see id.ac.itb.ee.lskk.lumen.core.impl.LumenPackageImpl#getLumenSysConfig()
		 * @generated
		 */
		EClass LUMEN_SYS_CONFIG = eINSTANCE.getLumenSysConfig();
		/**
		 * The meta object literal for the '<em><b>Tenant Env</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUMEN_SYS_CONFIG__TENANT_ENV = eINSTANCE.getLumenSysConfig_TenantEnv();

	}

} //LumenPackage
