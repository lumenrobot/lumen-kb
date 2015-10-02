/**
 */
package org.lskk.lumen.core;

import org.eclipse.emf.ecore.EFactory;
import org.lskk.lumen.core.impl.LumenFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see LumenPackage
 * @generated
 */
public interface LumenFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LumenFactory eINSTANCE = LumenFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Sys Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sys Config</em>'.
	 * @generated
	 */
	LumenSysConfig createLumenSysConfig();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	LumenPackage getLumenPackage();

} //LumenFactory
