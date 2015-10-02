/**
 */
package org.lskk.lumen.core;

import org.soluvas.commons.GeneralSysConfig;
import org.soluvas.commons.MongoSysConfig;
import org.soluvas.socmed.FacebookSysConfig;
import org.soluvas.socmed.InstagramSysConfig;
import org.soluvas.socmed.PinterestSysConfig;
import org.soluvas.socmed.TwitterSysConfig;
import org.soluvas.socmed.YouTubeSysConfig;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sys Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link LumenSysConfig#getTenantEnv <em>Tenant Env</em>}</li>
 * </ul>
 * </p>
 *
 * @see LumenPackage#getLumenSysConfig()
 * @model
 * @generated
 */
public interface LumenSysConfig extends GeneralSysConfig, MongoSysConfig, FacebookSysConfig, InstagramSysConfig, YouTubeSysConfig, PinterestSysConfig, TwitterSysConfig {

	/**
	 * Returns the value of the '<em><b>Tenant Env</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tenant Env</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tenant Env</em>' attribute.
	 * @see #setTenantEnv(String)
	 * @see LumenPackage#getLumenSysConfig_TenantEnv()
	 * @model
	 * @generated
	 */
	String getTenantEnv();

	/**
	 * Sets the value of the '{@link LumenSysConfig#getTenantEnv <em>Tenant Env</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tenant Env</em>' attribute.
	 * @see #getTenantEnv()
	 * @generated
	 */
	void setTenantEnv(String value);
} // LumenSysConfig
