/**
 */
package org.lskk.lumen.core.impl;

import id.ac.itb.ee.lskk.lumen.core.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.lskk.lumen.core.LumenFactory;
import org.lskk.lumen.core.LumenPackage;
import org.lskk.lumen.core.LumenSysConfig;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LumenFactoryImpl extends EFactoryImpl implements LumenFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LumenFactory init() {
		try {
			LumenFactory theLumenFactory = (LumenFactory)EPackage.Registry.INSTANCE.getEFactory(LumenPackage.eNS_URI);
			if (theLumenFactory != null) {
				return theLumenFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LumenFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LumenFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LumenPackage.LUMEN_SYS_CONFIG: return createLumenSysConfig();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LumenSysConfig createLumenSysConfig() {
		LumenSysConfigImpl lumenSysConfig = new LumenSysConfigImpl();
		return lumenSysConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LumenPackage getLumenPackage() {
		return (LumenPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static LumenPackage getPackage() {
		return LumenPackage.eINSTANCE;
	}

} //LumenFactoryImpl
