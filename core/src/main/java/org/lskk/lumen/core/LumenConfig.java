package org.lskk.lumen.core;

import com.google.common.base.Preconditions;
import org.lskk.lumen.core.yago.YagoAnswerer;
import org.lskk.lumen.core.yago.YagoEntityByLabelCacheStore;
import id.ac.itb.ee.lskk.relexid.core.RelExConfig;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.Locale;

import javax.inject.Inject;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridConfiguration;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridGainSpring;
import org.soluvas.commons.OnDemandXmiLoader;
import org.soluvas.commons.config.CommonsWebConfig;
import org.soluvas.mongo.MongoUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.mongodb.DB;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;

/**
 *
 * @author ceefour
 */
@Configuration
@Import({CommonsWebConfig.class, RelExConfig.class})
public class LumenConfig {

	public static final Locale ENGLISH = Locale.forLanguageTag("en-US");
	public static final Locale INDONESIAN = Locale.forLanguageTag("id-ID");

	@Bean
	public LumenSysConfig sysConfig() {
		return new OnDemandXmiLoader<LumenSysConfig>(
				LumenPackage.eINSTANCE, LumenConfig.class, "/META-INF/hotel.LumenSysConfig.xmi").get();
	}
	
	@Bean
	public FacebookChannel facebookChannel() {
		return new FacebookChannel();
	}
	
	// "classpath:" is required, otherwise it won't be found in a WAR
	@ImportResource("classpath:id/ac/itb/ee/lskk/lumen/core/lumen.gridgain.xml")
	@Configuration
	public static class GridGainConfig {
		
		@Inject
		private ApplicationContext appCtx;
		@Inject
		private GridConfiguration gridCfg;
		
		@Bean(destroyMethod="close")
		public Grid grid() throws GridException {
			return GridGainSpring.start(gridCfg, appCtx);
		}
		
	}
	
	@Bean
	public DB mongoDb() throws UnknownHostException, UnsupportedEncodingException {
		Preconditions.checkNotNull(sysConfig().getMongoUri(), "LumenSysConfig.mongoUri required");
		return MongoUtils.getDb(new MongoClientURI(sysConfig().getMongoUri()), ReadPreference.primary());
	}
	
	@Bean
	public YagoEntityByLabelCacheStore yagoEntityByLabelCacheStore() {
		return new YagoEntityByLabelCacheStore();
	}
	
	@Bean
	public YagoAnswerer yagoAnswerer() {
		return new YagoAnswerer();
	}
	
}
