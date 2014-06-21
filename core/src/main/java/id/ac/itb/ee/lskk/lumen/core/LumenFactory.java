/**
 */
package id.ac.itb.ee.lskk.lumen.core;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see id.ac.itb.ee.lskk.lumen.core.LumenPackage
 * @generated
 */
public interface LumenFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LumenFactory eINSTANCE = id.ac.itb.ee.lskk.lumen.core.impl.LumenFactoryImpl.init();

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
