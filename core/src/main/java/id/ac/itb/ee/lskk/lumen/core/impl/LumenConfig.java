package id.ac.itb.ee.lskk.lumen.core.impl;

import id.ac.itb.ee.lskk.lumen.core.FacebookChannel;
import id.ac.itb.ee.lskk.lumen.core.LumenPackage;
import id.ac.itb.ee.lskk.lumen.core.LumenSysConfig;
import id.ac.itb.ee.lskk.lumen.core.yago.YagoEntityByLabelCacheStore;
import id.ac.itb.ee.lskk.relexid.core.RelExConfig;

import java.net.URL;
import java.net.UnknownHostException;

import javax.inject.Inject;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridGainSpring;
import org.soluvas.commons.OnDemandXmiLoader;
import org.soluvas.commons.config.CommonsWebConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.base.Preconditions;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 *
 * @author ceefour
 */
@Configuration
@Import({CommonsWebConfig.class, RelExConfig.class})
public class LumenConfig {
	
	public static DB MONGODB;
	
	@Inject
	private ApplicationContext appCtx;
	
	@Bean
	public LumenSysConfig sysConfig() {
		return new OnDemandXmiLoader<LumenSysConfig>(
				LumenPackage.eINSTANCE, LumenConfig.class, "/META-INF/hotel.LumenSysConfig.xmi").get();
	}
	
	@Bean
	public FacebookChannel facebookChannel() {
		return new FacebookChannel();
	}
	
	@Bean(destroyMethod="close")
	public Grid grid() throws GridException, UnknownHostException {
		LumenConfig.MONGODB = mongoDb();
		final URL configUrl = Preconditions.checkNotNull(LumenPackage.class.getResource("lumen.gridgain.xml"),
				"Cannot find lumen.gridgain.xml");
		return GridGainSpring.start(configUrl, appCtx);
	}
	
	@Bean(destroyMethod="close")
	public MongoClient mongo() throws UnknownHostException {
		MongoClient mongo = new MongoClient(new MongoClientURI(sysConfig().getMongoUri()));
		return mongo;
	}
	
	@Bean
	public DB mongoDb() throws UnknownHostException {
		DB db = mongo().getDB("lumen_lumen_" + sysConfig().getTenantEnv());
		return db;
	}
	
	@Bean
	public YagoEntityByLabelCacheStore yagoEntityByLabelCacheStore() {
		return new YagoEntityByLabelCacheStore();
	}
	
}
