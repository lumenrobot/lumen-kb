/**
 */
package id.ac.itb.ee.lskk.lumen.core.util;

import id.ac.itb.ee.lskk.lumen.core.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.soluvas.commons.Expandable;
import org.soluvas.commons.GeneralSysConfig;
import org.soluvas.commons.MongoSysConfig;
import org.soluvas.commons.SysConfig;
import org.soluvas.commons.Timestamped;
import org.soluvas.socmed.FacebookSysConfig;
import org.soluvas.socmed.InstagramSysConfig;
import org.soluvas.socmed.PinterestSysConfig;
import org.soluvas.socmed.TwitterSysConfig;
import org.soluvas.socmed.YouTubeSysConfig;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see id.ac.itb.ee.lskk.lumen.core.LumenPackage
 * @generated
 */
public class LumenAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LumenPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LumenAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = LumenPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LumenSwitch<Adapter> modelSwitch =
		new LumenSwitch<Adapter>() {
			@Override
			public Adapter caseLumenSysConfig(LumenSysConfig object) {
				return createLumenSysConfigAdapter();
			}
			@Override
			public Adapter caseExpandable(Expandable object) {
				return createExpandableAdapter();
			}
			@Override
			public Adapter caseTimestamped(Timestamped object) {
				return createTimestampedAdapter();
			}
			@Override
			public Adapter caseSysConfig(SysConfig object) {
				return createSysConfigAdapter();
			}
			@Override
			public Adapter caseGeneralSysConfig(GeneralSysConfig object) {
				return createGeneralSysConfigAdapter();
			}
			@Override
			public Adapter caseMongoSysConfig(MongoSysConfig object) {
				return createMongoSysConfigAdapter();
			}
			@Override
			public Adapter caseFacebookSysConfig(FacebookSysConfig object) {
				return createFacebookSysConfigAdapter();
			}
			@Override
			public Adapter caseInstagramSysConfig(InstagramSysConfig object) {
				return createInstagramSysConfigAdapter();
			}
			@Override
			public Adapter caseYouTubeSysConfig(YouTubeSysConfig object) {
				return createYouTubeSysConfigAdapter();
			}
			@Override
			public Adapter casePinterestSysConfig(PinterestSysConfig object) {
				return createPinterestSysConfigAdapter();
			}
			@Override
			public Adapter caseTwitterSysConfig(TwitterSysConfig object) {
				return createTwitterSysConfigAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link id.ac.itb.ee.lskk.lumen.core.LumenSysConfig <em>Sys Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see id.ac.itb.ee.lskk.lumen.core.LumenSysConfig
	 * @generated
	 */
	public Adapter createLumenSysConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.soluvas.commons.Expandable <em>Expandable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.soluvas.commons.Expandable
	 * @generated
	 */
	public Adapter createExpandableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.soluvas.commons.Timestamped <em>Timestamped</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.soluvas.commons.Timestamped
	 * @generated
	 */
	public Adapter createTimestampedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.soluvas.commons.SysConfig <em>Sys Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.soluvas.commons.SysConfig
	 * @generated
	 */
	public Adapter createSysConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.soluvas.commons.GeneralSysConfig <em>General Sys Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.soluvas.commons.GeneralSysConfig
	 * @generated
	 */
	public Adapter createGeneralSysConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.soluvas.commons.MongoSysConfig <em>Mongo Sys Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.soluvas.commons.MongoSysConfig
	 * @generated
	 */
	public Adapter createMongoSysConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.soluvas.socmed.FacebookSysConfig <em>Facebook Sys Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.soluvas.socmed.FacebookSysConfig
	 * @generated
	 */
	public Adapter createFacebookSysConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.soluvas.socmed.InstagramSysConfig <em>Instagram Sys Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.soluvas.socmed.InstagramSysConfig
	 * @generated
	 */
	public Adapter createInstagramSysConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.soluvas.socmed.YouTubeSysConfig <em>You Tube Sys Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.soluvas.socmed.YouTubeSysConfig
	 * @generated
	 */
	public Adapter createYouTubeSysConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.soluvas.socmed.PinterestSysConfig <em>Pinterest Sys Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.soluvas.socmed.PinterestSysConfig
	 * @generated
	 */
	public Adapter createPinterestSysConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.soluvas.socmed.TwitterSysConfig <em>Twitter Sys Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.soluvas.socmed.TwitterSysConfig
	 * @generated
	 */
	public Adapter createTwitterSysConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //LumenAdapterFactory
