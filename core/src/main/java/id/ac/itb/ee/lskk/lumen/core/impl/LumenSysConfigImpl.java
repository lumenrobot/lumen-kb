/**
 */
package id.ac.itb.ee.lskk.lumen.core.impl;

import id.ac.itb.ee.lskk.lumen.core.LumenPackage;
import id.ac.itb.ee.lskk.lumen.core.LumenSysConfig;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.soluvas.commons.CommonsPackage;
import org.soluvas.commons.MongoSysConfig;
import org.soluvas.commons.impl.GeneralSysConfigImpl;
import org.soluvas.socmed.FacebookSysConfig;
import org.soluvas.socmed.InstagramSysConfig;
import org.soluvas.socmed.PinterestSysConfig;
import org.soluvas.socmed.SocmedPackage;
import org.soluvas.socmed.TwitterSysConfig;
import org.soluvas.socmed.YouTubeSysConfig;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sys Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getMongoUri <em>Mongo Uri</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getFacebookAppId <em>Facebook App Id</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getFacebookAppSecret <em>Facebook App Secret</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getFacebookTenantAccessToken <em>Facebook Tenant Access Token</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getFacebookProfileId <em>Facebook Profile Id</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getFacebookProfileUsername <em>Facebook Profile Username</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getFacebookProfileName <em>Facebook Profile Name</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getFacebookTenantPageId <em>Facebook Tenant Page Id</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getFacebookTenantPageUsername <em>Facebook Tenant Page Username</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getFacebookTenantPublishEnabled <em>Facebook Tenant Publish Enabled</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getFacebookExplicitlyShared <em>Facebook Explicitly Shared</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getInstagramScreenName <em>Instagram Screen Name</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getYouTubeTenantScreenName <em>You Tube Tenant Screen Name</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getPinterestTenantScreenName <em>Pinterest Tenant Screen Name</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getTwitterConsumerKey <em>Twitter Consumer Key</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getTwitterConsumerSecret <em>Twitter Consumer Secret</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getTwitterTenantScreenName <em>Twitter Tenant Screen Name</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getTwitterTenantAccessToken <em>Twitter Tenant Access Token</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getTwitterTenantAccessTokenSecret <em>Twitter Tenant Access Token Secret</em>}</li>
 *   <li>{@link id.ac.itb.ee.lskk.lumen.core.impl.LumenSysConfigImpl#getTenantEnv <em>Tenant Env</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LumenSysConfigImpl extends GeneralSysConfigImpl implements LumenSysConfig {
	/**
	 * The default value of the '{@link #getMongoUri() <em>Mongo Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMongoUri()
	 * @generated
	 * @ordered
	 */
	protected static final String MONGO_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMongoUri() <em>Mongo Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMongoUri()
	 * @generated
	 * @ordered
	 */
	protected String mongoUri = MONGO_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacebookAppId() <em>Facebook App Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookAppId()
	 * @generated
	 * @ordered
	 */
	protected static final Long FACEBOOK_APP_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFacebookAppId() <em>Facebook App Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookAppId()
	 * @generated
	 * @ordered
	 */
	protected Long facebookAppId = FACEBOOK_APP_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacebookAppSecret() <em>Facebook App Secret</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookAppSecret()
	 * @generated
	 * @ordered
	 */
	protected static final String FACEBOOK_APP_SECRET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFacebookAppSecret() <em>Facebook App Secret</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookAppSecret()
	 * @generated
	 * @ordered
	 */
	protected String facebookAppSecret = FACEBOOK_APP_SECRET_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacebookTenantAccessToken() <em>Facebook Tenant Access Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookTenantAccessToken()
	 * @generated
	 * @ordered
	 */
	protected static final String FACEBOOK_TENANT_ACCESS_TOKEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFacebookTenantAccessToken() <em>Facebook Tenant Access Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookTenantAccessToken()
	 * @generated
	 * @ordered
	 */
	protected String facebookTenantAccessToken = FACEBOOK_TENANT_ACCESS_TOKEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacebookProfileId() <em>Facebook Profile Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookProfileId()
	 * @generated
	 * @ordered
	 */
	protected static final Long FACEBOOK_PROFILE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFacebookProfileId() <em>Facebook Profile Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookProfileId()
	 * @generated
	 * @ordered
	 */
	protected Long facebookProfileId = FACEBOOK_PROFILE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacebookProfileUsername() <em>Facebook Profile Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookProfileUsername()
	 * @generated
	 * @ordered
	 */
	protected static final String FACEBOOK_PROFILE_USERNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFacebookProfileUsername() <em>Facebook Profile Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookProfileUsername()
	 * @generated
	 * @ordered
	 */
	protected String facebookProfileUsername = FACEBOOK_PROFILE_USERNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacebookProfileName() <em>Facebook Profile Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookProfileName()
	 * @generated
	 * @ordered
	 */
	protected static final String FACEBOOK_PROFILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFacebookProfileName() <em>Facebook Profile Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookProfileName()
	 * @generated
	 * @ordered
	 */
	protected String facebookProfileName = FACEBOOK_PROFILE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacebookTenantPageId() <em>Facebook Tenant Page Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookTenantPageId()
	 * @generated
	 * @ordered
	 */
	protected static final Long FACEBOOK_TENANT_PAGE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFacebookTenantPageId() <em>Facebook Tenant Page Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookTenantPageId()
	 * @generated
	 * @ordered
	 */
	protected Long facebookTenantPageId = FACEBOOK_TENANT_PAGE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacebookTenantPageUsername() <em>Facebook Tenant Page Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookTenantPageUsername()
	 * @generated
	 * @ordered
	 */
	protected static final String FACEBOOK_TENANT_PAGE_USERNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFacebookTenantPageUsername() <em>Facebook Tenant Page Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookTenantPageUsername()
	 * @generated
	 * @ordered
	 */
	protected String facebookTenantPageUsername = FACEBOOK_TENANT_PAGE_USERNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacebookTenantPublishEnabled() <em>Facebook Tenant Publish Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookTenantPublishEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FACEBOOK_TENANT_PUBLISH_ENABLED_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getFacebookTenantPublishEnabled() <em>Facebook Tenant Publish Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookTenantPublishEnabled()
	 * @generated
	 * @ordered
	 */
	protected Boolean facebookTenantPublishEnabled = FACEBOOK_TENANT_PUBLISH_ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getFacebookExplicitlyShared() <em>Facebook Explicitly Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookExplicitlyShared()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FACEBOOK_EXPLICITLY_SHARED_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getFacebookExplicitlyShared() <em>Facebook Explicitly Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFacebookExplicitlyShared()
	 * @generated
	 * @ordered
	 */
	protected Boolean facebookExplicitlyShared = FACEBOOK_EXPLICITLY_SHARED_EDEFAULT;

	/**
	 * The default value of the '{@link #getInstagramScreenName() <em>Instagram Screen Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstagramScreenName()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTAGRAM_SCREEN_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInstagramScreenName() <em>Instagram Screen Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstagramScreenName()
	 * @generated
	 * @ordered
	 */
	protected String instagramScreenName = INSTAGRAM_SCREEN_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getYouTubeTenantScreenName() <em>You Tube Tenant Screen Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYouTubeTenantScreenName()
	 * @generated
	 * @ordered
	 */
	protected static final String YOU_TUBE_TENANT_SCREEN_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getYouTubeTenantScreenName() <em>You Tube Tenant Screen Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYouTubeTenantScreenName()
	 * @generated
	 * @ordered
	 */
	protected String youTubeTenantScreenName = YOU_TUBE_TENANT_SCREEN_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPinterestTenantScreenName() <em>Pinterest Tenant Screen Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPinterestTenantScreenName()
	 * @generated
	 * @ordered
	 */
	protected static final String PINTEREST_TENANT_SCREEN_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPinterestTenantScreenName() <em>Pinterest Tenant Screen Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPinterestTenantScreenName()
	 * @generated
	 * @ordered
	 */
	protected String pinterestTenantScreenName = PINTEREST_TENANT_SCREEN_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTwitterConsumerKey() <em>Twitter Consumer Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTwitterConsumerKey()
	 * @generated
	 * @ordered
	 */
	protected static final String TWITTER_CONSUMER_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTwitterConsumerKey() <em>Twitter Consumer Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTwitterConsumerKey()
	 * @generated
	 * @ordered
	 */
	protected String twitterConsumerKey = TWITTER_CONSUMER_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getTwitterConsumerSecret() <em>Twitter Consumer Secret</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTwitterConsumerSecret()
	 * @generated
	 * @ordered
	 */
	protected static final String TWITTER_CONSUMER_SECRET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTwitterConsumerSecret() <em>Twitter Consumer Secret</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTwitterConsumerSecret()
	 * @generated
	 * @ordered
	 */
	protected String twitterConsumerSecret = TWITTER_CONSUMER_SECRET_EDEFAULT;

	/**
	 * The default value of the '{@link #getTwitterTenantScreenName() <em>Twitter Tenant Screen Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTwitterTenantScreenName()
	 * @generated
	 * @ordered
	 */
	protected static final String TWITTER_TENANT_SCREEN_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTwitterTenantScreenName() <em>Twitter Tenant Screen Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTwitterTenantScreenName()
	 * @generated
	 * @ordered
	 */
	protected String twitterTenantScreenName = TWITTER_TENANT_SCREEN_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTwitterTenantAccessToken() <em>Twitter Tenant Access Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTwitterTenantAccessToken()
	 * @generated
	 * @ordered
	 */
	protected static final String TWITTER_TENANT_ACCESS_TOKEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTwitterTenantAccessToken() <em>Twitter Tenant Access Token</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTwitterTenantAccessToken()
	 * @generated
	 * @ordered
	 */
	protected String twitterTenantAccessToken = TWITTER_TENANT_ACCESS_TOKEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getTwitterTenantAccessTokenSecret() <em>Twitter Tenant Access Token Secret</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTwitterTenantAccessTokenSecret()
	 * @generated
	 * @ordered
	 */
	protected static final String TWITTER_TENANT_ACCESS_TOKEN_SECRET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTwitterTenantAccessTokenSecret() <em>Twitter Tenant Access Token Secret</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTwitterTenantAccessTokenSecret()
	 * @generated
	 * @ordered
	 */
	protected String twitterTenantAccessTokenSecret = TWITTER_TENANT_ACCESS_TOKEN_SECRET_EDEFAULT;

	/**
	 * The default value of the '{@link #getTenantEnv() <em>Tenant Env</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTenantEnv()
	 * @generated
	 * @ordered
	 */
	protected static final String TENANT_ENV_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTenantEnv() <em>Tenant Env</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTenantEnv()
	 * @generated
	 * @ordered
	 */
	protected String tenantEnv = TENANT_ENV_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LumenSysConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LumenPackage.Literals.LUMEN_SYS_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMongoUri() {
		return mongoUri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMongoUri(String newMongoUri) {
		String oldMongoUri = mongoUri;
		mongoUri = newMongoUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__MONGO_URI, oldMongoUri, mongoUri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getFacebookAppId() {
		return facebookAppId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacebookAppId(Long newFacebookAppId) {
		Long oldFacebookAppId = facebookAppId;
		facebookAppId = newFacebookAppId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_ID, oldFacebookAppId, facebookAppId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFacebookAppSecret() {
		return facebookAppSecret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacebookAppSecret(String newFacebookAppSecret) {
		String oldFacebookAppSecret = facebookAppSecret;
		facebookAppSecret = newFacebookAppSecret;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_SECRET, oldFacebookAppSecret, facebookAppSecret));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFacebookTenantAccessToken() {
		return facebookTenantAccessToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacebookTenantAccessToken(String newFacebookTenantAccessToken) {
		String oldFacebookTenantAccessToken = facebookTenantAccessToken;
		facebookTenantAccessToken = newFacebookTenantAccessToken;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_ACCESS_TOKEN, oldFacebookTenantAccessToken, facebookTenantAccessToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getFacebookProfileId() {
		return facebookProfileId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacebookProfileId(Long newFacebookProfileId) {
		Long oldFacebookProfileId = facebookProfileId;
		facebookProfileId = newFacebookProfileId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_ID, oldFacebookProfileId, facebookProfileId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFacebookProfileUsername() {
		return facebookProfileUsername;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacebookProfileUsername(String newFacebookProfileUsername) {
		String oldFacebookProfileUsername = facebookProfileUsername;
		facebookProfileUsername = newFacebookProfileUsername;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_USERNAME, oldFacebookProfileUsername, facebookProfileUsername));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFacebookProfileName() {
		return facebookProfileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacebookProfileName(String newFacebookProfileName) {
		String oldFacebookProfileName = facebookProfileName;
		facebookProfileName = newFacebookProfileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_NAME, oldFacebookProfileName, facebookProfileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getFacebookTenantPageId() {
		return facebookTenantPageId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacebookTenantPageId(Long newFacebookTenantPageId) {
		Long oldFacebookTenantPageId = facebookTenantPageId;
		facebookTenantPageId = newFacebookTenantPageId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_ID, oldFacebookTenantPageId, facebookTenantPageId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFacebookTenantPageUsername() {
		return facebookTenantPageUsername;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacebookTenantPageUsername(String newFacebookTenantPageUsername) {
		String oldFacebookTenantPageUsername = facebookTenantPageUsername;
		facebookTenantPageUsername = newFacebookTenantPageUsername;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_USERNAME, oldFacebookTenantPageUsername, facebookTenantPageUsername));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFacebookTenantPublishEnabled() {
		return facebookTenantPublishEnabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacebookTenantPublishEnabled(Boolean newFacebookTenantPublishEnabled) {
		Boolean oldFacebookTenantPublishEnabled = facebookTenantPublishEnabled;
		facebookTenantPublishEnabled = newFacebookTenantPublishEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PUBLISH_ENABLED, oldFacebookTenantPublishEnabled, facebookTenantPublishEnabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFacebookExplicitlyShared() {
		return facebookExplicitlyShared;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFacebookExplicitlyShared(Boolean newFacebookExplicitlyShared) {
		Boolean oldFacebookExplicitlyShared = facebookExplicitlyShared;
		facebookExplicitlyShared = newFacebookExplicitlyShared;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_EXPLICITLY_SHARED, oldFacebookExplicitlyShared, facebookExplicitlyShared));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInstagramScreenName() {
		return instagramScreenName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstagramScreenName(String newInstagramScreenName) {
		String oldInstagramScreenName = instagramScreenName;
		instagramScreenName = newInstagramScreenName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__INSTAGRAM_SCREEN_NAME, oldInstagramScreenName, instagramScreenName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getYouTubeTenantScreenName() {
		return youTubeTenantScreenName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYouTubeTenantScreenName(String newYouTubeTenantScreenName) {
		String oldYouTubeTenantScreenName = youTubeTenantScreenName;
		youTubeTenantScreenName = newYouTubeTenantScreenName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__YOU_TUBE_TENANT_SCREEN_NAME, oldYouTubeTenantScreenName, youTubeTenantScreenName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPinterestTenantScreenName() {
		return pinterestTenantScreenName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPinterestTenantScreenName(String newPinterestTenantScreenName) {
		String oldPinterestTenantScreenName = pinterestTenantScreenName;
		pinterestTenantScreenName = newPinterestTenantScreenName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__PINTEREST_TENANT_SCREEN_NAME, oldPinterestTenantScreenName, pinterestTenantScreenName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTwitterConsumerKey() {
		return twitterConsumerKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTwitterConsumerKey(String newTwitterConsumerKey) {
		String oldTwitterConsumerKey = twitterConsumerKey;
		twitterConsumerKey = newTwitterConsumerKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_KEY, oldTwitterConsumerKey, twitterConsumerKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTwitterConsumerSecret() {
		return twitterConsumerSecret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTwitterConsumerSecret(String newTwitterConsumerSecret) {
		String oldTwitterConsumerSecret = twitterConsumerSecret;
		twitterConsumerSecret = newTwitterConsumerSecret;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_SECRET, oldTwitterConsumerSecret, twitterConsumerSecret));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTwitterTenantScreenName() {
		return twitterTenantScreenName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTwitterTenantScreenName(String newTwitterTenantScreenName) {
		String oldTwitterTenantScreenName = twitterTenantScreenName;
		twitterTenantScreenName = newTwitterTenantScreenName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_SCREEN_NAME, oldTwitterTenantScreenName, twitterTenantScreenName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTwitterTenantAccessToken() {
		return twitterTenantAccessToken;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTwitterTenantAccessToken(String newTwitterTenantAccessToken) {
		String oldTwitterTenantAccessToken = twitterTenantAccessToken;
		twitterTenantAccessToken = newTwitterTenantAccessToken;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN, oldTwitterTenantAccessToken, twitterTenantAccessToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTwitterTenantAccessTokenSecret() {
		return twitterTenantAccessTokenSecret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTwitterTenantAccessTokenSecret(String newTwitterTenantAccessTokenSecret) {
		String oldTwitterTenantAccessTokenSecret = twitterTenantAccessTokenSecret;
		twitterTenantAccessTokenSecret = newTwitterTenantAccessTokenSecret;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN_SECRET, oldTwitterTenantAccessTokenSecret, twitterTenantAccessTokenSecret));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTenantEnv() {
		return tenantEnv;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTenantEnv(String newTenantEnv) {
		String oldTenantEnv = tenantEnv;
		tenantEnv = newTenantEnv;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LumenPackage.LUMEN_SYS_CONFIG__TENANT_ENV, oldTenantEnv, tenantEnv));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LumenPackage.LUMEN_SYS_CONFIG__MONGO_URI:
				return getMongoUri();
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_ID:
				return getFacebookAppId();
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_SECRET:
				return getFacebookAppSecret();
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_ACCESS_TOKEN:
				return getFacebookTenantAccessToken();
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_ID:
				return getFacebookProfileId();
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_USERNAME:
				return getFacebookProfileUsername();
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_NAME:
				return getFacebookProfileName();
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_ID:
				return getFacebookTenantPageId();
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_USERNAME:
				return getFacebookTenantPageUsername();
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PUBLISH_ENABLED:
				return getFacebookTenantPublishEnabled();
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_EXPLICITLY_SHARED:
				return getFacebookExplicitlyShared();
			case LumenPackage.LUMEN_SYS_CONFIG__INSTAGRAM_SCREEN_NAME:
				return getInstagramScreenName();
			case LumenPackage.LUMEN_SYS_CONFIG__YOU_TUBE_TENANT_SCREEN_NAME:
				return getYouTubeTenantScreenName();
			case LumenPackage.LUMEN_SYS_CONFIG__PINTEREST_TENANT_SCREEN_NAME:
				return getPinterestTenantScreenName();
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_KEY:
				return getTwitterConsumerKey();
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_SECRET:
				return getTwitterConsumerSecret();
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_SCREEN_NAME:
				return getTwitterTenantScreenName();
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN:
				return getTwitterTenantAccessToken();
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN_SECRET:
				return getTwitterTenantAccessTokenSecret();
			case LumenPackage.LUMEN_SYS_CONFIG__TENANT_ENV:
				return getTenantEnv();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LumenPackage.LUMEN_SYS_CONFIG__MONGO_URI:
				setMongoUri((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_ID:
				setFacebookAppId((Long)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_SECRET:
				setFacebookAppSecret((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_ACCESS_TOKEN:
				setFacebookTenantAccessToken((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_ID:
				setFacebookProfileId((Long)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_USERNAME:
				setFacebookProfileUsername((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_NAME:
				setFacebookProfileName((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_ID:
				setFacebookTenantPageId((Long)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_USERNAME:
				setFacebookTenantPageUsername((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PUBLISH_ENABLED:
				setFacebookTenantPublishEnabled((Boolean)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_EXPLICITLY_SHARED:
				setFacebookExplicitlyShared((Boolean)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__INSTAGRAM_SCREEN_NAME:
				setInstagramScreenName((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__YOU_TUBE_TENANT_SCREEN_NAME:
				setYouTubeTenantScreenName((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__PINTEREST_TENANT_SCREEN_NAME:
				setPinterestTenantScreenName((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_KEY:
				setTwitterConsumerKey((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_SECRET:
				setTwitterConsumerSecret((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_SCREEN_NAME:
				setTwitterTenantScreenName((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN:
				setTwitterTenantAccessToken((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN_SECRET:
				setTwitterTenantAccessTokenSecret((String)newValue);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TENANT_ENV:
				setTenantEnv((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case LumenPackage.LUMEN_SYS_CONFIG__MONGO_URI:
				setMongoUri(MONGO_URI_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_ID:
				setFacebookAppId(FACEBOOK_APP_ID_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_SECRET:
				setFacebookAppSecret(FACEBOOK_APP_SECRET_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_ACCESS_TOKEN:
				setFacebookTenantAccessToken(FACEBOOK_TENANT_ACCESS_TOKEN_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_ID:
				setFacebookProfileId(FACEBOOK_PROFILE_ID_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_USERNAME:
				setFacebookProfileUsername(FACEBOOK_PROFILE_USERNAME_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_NAME:
				setFacebookProfileName(FACEBOOK_PROFILE_NAME_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_ID:
				setFacebookTenantPageId(FACEBOOK_TENANT_PAGE_ID_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_USERNAME:
				setFacebookTenantPageUsername(FACEBOOK_TENANT_PAGE_USERNAME_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PUBLISH_ENABLED:
				setFacebookTenantPublishEnabled(FACEBOOK_TENANT_PUBLISH_ENABLED_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_EXPLICITLY_SHARED:
				setFacebookExplicitlyShared(FACEBOOK_EXPLICITLY_SHARED_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__INSTAGRAM_SCREEN_NAME:
				setInstagramScreenName(INSTAGRAM_SCREEN_NAME_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__YOU_TUBE_TENANT_SCREEN_NAME:
				setYouTubeTenantScreenName(YOU_TUBE_TENANT_SCREEN_NAME_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__PINTEREST_TENANT_SCREEN_NAME:
				setPinterestTenantScreenName(PINTEREST_TENANT_SCREEN_NAME_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_KEY:
				setTwitterConsumerKey(TWITTER_CONSUMER_KEY_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_SECRET:
				setTwitterConsumerSecret(TWITTER_CONSUMER_SECRET_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_SCREEN_NAME:
				setTwitterTenantScreenName(TWITTER_TENANT_SCREEN_NAME_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN:
				setTwitterTenantAccessToken(TWITTER_TENANT_ACCESS_TOKEN_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN_SECRET:
				setTwitterTenantAccessTokenSecret(TWITTER_TENANT_ACCESS_TOKEN_SECRET_EDEFAULT);
				return;
			case LumenPackage.LUMEN_SYS_CONFIG__TENANT_ENV:
				setTenantEnv(TENANT_ENV_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LumenPackage.LUMEN_SYS_CONFIG__MONGO_URI:
				return MONGO_URI_EDEFAULT == null ? mongoUri != null : !MONGO_URI_EDEFAULT.equals(mongoUri);
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_ID:
				return FACEBOOK_APP_ID_EDEFAULT == null ? facebookAppId != null : !FACEBOOK_APP_ID_EDEFAULT.equals(facebookAppId);
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_SECRET:
				return FACEBOOK_APP_SECRET_EDEFAULT == null ? facebookAppSecret != null : !FACEBOOK_APP_SECRET_EDEFAULT.equals(facebookAppSecret);
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_ACCESS_TOKEN:
				return FACEBOOK_TENANT_ACCESS_TOKEN_EDEFAULT == null ? facebookTenantAccessToken != null : !FACEBOOK_TENANT_ACCESS_TOKEN_EDEFAULT.equals(facebookTenantAccessToken);
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_ID:
				return FACEBOOK_PROFILE_ID_EDEFAULT == null ? facebookProfileId != null : !FACEBOOK_PROFILE_ID_EDEFAULT.equals(facebookProfileId);
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_USERNAME:
				return FACEBOOK_PROFILE_USERNAME_EDEFAULT == null ? facebookProfileUsername != null : !FACEBOOK_PROFILE_USERNAME_EDEFAULT.equals(facebookProfileUsername);
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_NAME:
				return FACEBOOK_PROFILE_NAME_EDEFAULT == null ? facebookProfileName != null : !FACEBOOK_PROFILE_NAME_EDEFAULT.equals(facebookProfileName);
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_ID:
				return FACEBOOK_TENANT_PAGE_ID_EDEFAULT == null ? facebookTenantPageId != null : !FACEBOOK_TENANT_PAGE_ID_EDEFAULT.equals(facebookTenantPageId);
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_USERNAME:
				return FACEBOOK_TENANT_PAGE_USERNAME_EDEFAULT == null ? facebookTenantPageUsername != null : !FACEBOOK_TENANT_PAGE_USERNAME_EDEFAULT.equals(facebookTenantPageUsername);
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PUBLISH_ENABLED:
				return FACEBOOK_TENANT_PUBLISH_ENABLED_EDEFAULT == null ? facebookTenantPublishEnabled != null : !FACEBOOK_TENANT_PUBLISH_ENABLED_EDEFAULT.equals(facebookTenantPublishEnabled);
			case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_EXPLICITLY_SHARED:
				return FACEBOOK_EXPLICITLY_SHARED_EDEFAULT == null ? facebookExplicitlyShared != null : !FACEBOOK_EXPLICITLY_SHARED_EDEFAULT.equals(facebookExplicitlyShared);
			case LumenPackage.LUMEN_SYS_CONFIG__INSTAGRAM_SCREEN_NAME:
				return INSTAGRAM_SCREEN_NAME_EDEFAULT == null ? instagramScreenName != null : !INSTAGRAM_SCREEN_NAME_EDEFAULT.equals(instagramScreenName);
			case LumenPackage.LUMEN_SYS_CONFIG__YOU_TUBE_TENANT_SCREEN_NAME:
				return YOU_TUBE_TENANT_SCREEN_NAME_EDEFAULT == null ? youTubeTenantScreenName != null : !YOU_TUBE_TENANT_SCREEN_NAME_EDEFAULT.equals(youTubeTenantScreenName);
			case LumenPackage.LUMEN_SYS_CONFIG__PINTEREST_TENANT_SCREEN_NAME:
				return PINTEREST_TENANT_SCREEN_NAME_EDEFAULT == null ? pinterestTenantScreenName != null : !PINTEREST_TENANT_SCREEN_NAME_EDEFAULT.equals(pinterestTenantScreenName);
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_KEY:
				return TWITTER_CONSUMER_KEY_EDEFAULT == null ? twitterConsumerKey != null : !TWITTER_CONSUMER_KEY_EDEFAULT.equals(twitterConsumerKey);
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_SECRET:
				return TWITTER_CONSUMER_SECRET_EDEFAULT == null ? twitterConsumerSecret != null : !TWITTER_CONSUMER_SECRET_EDEFAULT.equals(twitterConsumerSecret);
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_SCREEN_NAME:
				return TWITTER_TENANT_SCREEN_NAME_EDEFAULT == null ? twitterTenantScreenName != null : !TWITTER_TENANT_SCREEN_NAME_EDEFAULT.equals(twitterTenantScreenName);
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN:
				return TWITTER_TENANT_ACCESS_TOKEN_EDEFAULT == null ? twitterTenantAccessToken != null : !TWITTER_TENANT_ACCESS_TOKEN_EDEFAULT.equals(twitterTenantAccessToken);
			case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN_SECRET:
				return TWITTER_TENANT_ACCESS_TOKEN_SECRET_EDEFAULT == null ? twitterTenantAccessTokenSecret != null : !TWITTER_TENANT_ACCESS_TOKEN_SECRET_EDEFAULT.equals(twitterTenantAccessTokenSecret);
			case LumenPackage.LUMEN_SYS_CONFIG__TENANT_ENV:
				return TENANT_ENV_EDEFAULT == null ? tenantEnv != null : !TENANT_ENV_EDEFAULT.equals(tenantEnv);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == MongoSysConfig.class) {
			switch (derivedFeatureID) {
				case LumenPackage.LUMEN_SYS_CONFIG__MONGO_URI: return CommonsPackage.MONGO_SYS_CONFIG__MONGO_URI;
				default: return -1;
			}
		}
		if (baseClass == FacebookSysConfig.class) {
			switch (derivedFeatureID) {
				case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_ID: return SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_APP_ID;
				case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_SECRET: return SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_APP_SECRET;
				case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_ACCESS_TOKEN: return SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_TENANT_ACCESS_TOKEN;
				case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_ID: return SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_PROFILE_ID;
				case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_USERNAME: return SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_PROFILE_USERNAME;
				case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_NAME: return SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_PROFILE_NAME;
				case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_ID: return SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_TENANT_PAGE_ID;
				case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_USERNAME: return SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_TENANT_PAGE_USERNAME;
				case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PUBLISH_ENABLED: return SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_TENANT_PUBLISH_ENABLED;
				case LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_EXPLICITLY_SHARED: return SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_EXPLICITLY_SHARED;
				default: return -1;
			}
		}
		if (baseClass == InstagramSysConfig.class) {
			switch (derivedFeatureID) {
				case LumenPackage.LUMEN_SYS_CONFIG__INSTAGRAM_SCREEN_NAME: return SocmedPackage.INSTAGRAM_SYS_CONFIG__INSTAGRAM_SCREEN_NAME;
				default: return -1;
			}
		}
		if (baseClass == YouTubeSysConfig.class) {
			switch (derivedFeatureID) {
				case LumenPackage.LUMEN_SYS_CONFIG__YOU_TUBE_TENANT_SCREEN_NAME: return SocmedPackage.YOU_TUBE_SYS_CONFIG__YOU_TUBE_TENANT_SCREEN_NAME;
				default: return -1;
			}
		}
		if (baseClass == PinterestSysConfig.class) {
			switch (derivedFeatureID) {
				case LumenPackage.LUMEN_SYS_CONFIG__PINTEREST_TENANT_SCREEN_NAME: return SocmedPackage.PINTEREST_SYS_CONFIG__PINTEREST_TENANT_SCREEN_NAME;
				default: return -1;
			}
		}
		if (baseClass == TwitterSysConfig.class) {
			switch (derivedFeatureID) {
				case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_KEY: return SocmedPackage.TWITTER_SYS_CONFIG__TWITTER_CONSUMER_KEY;
				case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_SECRET: return SocmedPackage.TWITTER_SYS_CONFIG__TWITTER_CONSUMER_SECRET;
				case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_SCREEN_NAME: return SocmedPackage.TWITTER_SYS_CONFIG__TWITTER_TENANT_SCREEN_NAME;
				case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN: return SocmedPackage.TWITTER_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN;
				case LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN_SECRET: return SocmedPackage.TWITTER_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN_SECRET;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == MongoSysConfig.class) {
			switch (baseFeatureID) {
				case CommonsPackage.MONGO_SYS_CONFIG__MONGO_URI: return LumenPackage.LUMEN_SYS_CONFIG__MONGO_URI;
				default: return -1;
			}
		}
		if (baseClass == FacebookSysConfig.class) {
			switch (baseFeatureID) {
				case SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_APP_ID: return LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_ID;
				case SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_APP_SECRET: return LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_APP_SECRET;
				case SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_TENANT_ACCESS_TOKEN: return LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_ACCESS_TOKEN;
				case SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_PROFILE_ID: return LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_ID;
				case SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_PROFILE_USERNAME: return LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_USERNAME;
				case SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_PROFILE_NAME: return LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_PROFILE_NAME;
				case SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_TENANT_PAGE_ID: return LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_ID;
				case SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_TENANT_PAGE_USERNAME: return LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PAGE_USERNAME;
				case SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_TENANT_PUBLISH_ENABLED: return LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_TENANT_PUBLISH_ENABLED;
				case SocmedPackage.FACEBOOK_SYS_CONFIG__FACEBOOK_EXPLICITLY_SHARED: return LumenPackage.LUMEN_SYS_CONFIG__FACEBOOK_EXPLICITLY_SHARED;
				default: return -1;
			}
		}
		if (baseClass == InstagramSysConfig.class) {
			switch (baseFeatureID) {
				case SocmedPackage.INSTAGRAM_SYS_CONFIG__INSTAGRAM_SCREEN_NAME: return LumenPackage.LUMEN_SYS_CONFIG__INSTAGRAM_SCREEN_NAME;
				default: return -1;
			}
		}
		if (baseClass == YouTubeSysConfig.class) {
			switch (baseFeatureID) {
				case SocmedPackage.YOU_TUBE_SYS_CONFIG__YOU_TUBE_TENANT_SCREEN_NAME: return LumenPackage.LUMEN_SYS_CONFIG__YOU_TUBE_TENANT_SCREEN_NAME;
				default: return -1;
			}
		}
		if (baseClass == PinterestSysConfig.class) {
			switch (baseFeatureID) {
				case SocmedPackage.PINTEREST_SYS_CONFIG__PINTEREST_TENANT_SCREEN_NAME: return LumenPackage.LUMEN_SYS_CONFIG__PINTEREST_TENANT_SCREEN_NAME;
				default: return -1;
			}
		}
		if (baseClass == TwitterSysConfig.class) {
			switch (baseFeatureID) {
				case SocmedPackage.TWITTER_SYS_CONFIG__TWITTER_CONSUMER_KEY: return LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_KEY;
				case SocmedPackage.TWITTER_SYS_CONFIG__TWITTER_CONSUMER_SECRET: return LumenPackage.LUMEN_SYS_CONFIG__TWITTER_CONSUMER_SECRET;
				case SocmedPackage.TWITTER_SYS_CONFIG__TWITTER_TENANT_SCREEN_NAME: return LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_SCREEN_NAME;
				case SocmedPackage.TWITTER_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN: return LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN;
				case SocmedPackage.TWITTER_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN_SECRET: return LumenPackage.LUMEN_SYS_CONFIG__TWITTER_TENANT_ACCESS_TOKEN_SECRET;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mongoUri: ");
		result.append(mongoUri);
		result.append(", facebookAppId: ");
		result.append(facebookAppId);
		result.append(", facebookAppSecret: ");
		result.append(facebookAppSecret);
		result.append(", facebookTenantAccessToken: ");
		result.append(facebookTenantAccessToken);
		result.append(", facebookProfileId: ");
		result.append(facebookProfileId);
		result.append(", facebookProfileUsername: ");
		result.append(facebookProfileUsername);
		result.append(", facebookProfileName: ");
		result.append(facebookProfileName);
		result.append(", facebookTenantPageId: ");
		result.append(facebookTenantPageId);
		result.append(", facebookTenantPageUsername: ");
		result.append(facebookTenantPageUsername);
		result.append(", facebookTenantPublishEnabled: ");
		result.append(facebookTenantPublishEnabled);
		result.append(", facebookExplicitlyShared: ");
		result.append(facebookExplicitlyShared);
		result.append(", instagramScreenName: ");
		result.append(instagramScreenName);
		result.append(", youTubeTenantScreenName: ");
		result.append(youTubeTenantScreenName);
		result.append(", pinterestTenantScreenName: ");
		result.append(pinterestTenantScreenName);
		result.append(", twitterConsumerKey: ");
		result.append(twitterConsumerKey);
		result.append(", twitterConsumerSecret: ");
		result.append(twitterConsumerSecret);
		result.append(", twitterTenantScreenName: ");
		result.append(twitterTenantScreenName);
		result.append(", twitterTenantAccessToken: ");
		result.append(twitterTenantAccessToken);
		result.append(", twitterTenantAccessTokenSecret: ");
		result.append(twitterTenantAccessTokenSecret);
		result.append(", tenantEnv: ");
		result.append(tenantEnv);
		result.append(')');
		return result.toString();
	}

} //LumenSysConfigImpl
