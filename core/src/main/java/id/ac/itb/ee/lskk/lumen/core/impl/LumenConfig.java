package id.ac.itb.ee.lskk.lumen.core.impl;

import id.ac.itb.ee.lskk.lumen.core.FacebookChannel;
import id.ac.itb.ee.lskk.lumen.core.LumenPackage;
import id.ac.itb.ee.lskk.lumen.core.LumenSysConfig;
import id.ac.itb.ee.lskk.relexid.core.RelEx;

import org.soluvas.commons.OnDemandXmiLoader;
import org.soluvas.commons.config.CommonsWebConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.collect.ImmutableMap;

/**
 *
 * @author ceefour
 */
@Configuration
@Import(CommonsWebConfig.class)
public class LumenConfig {
	
	@Bean
	public LumenSysConfig sysConfig() {
		return new OnDemandXmiLoader<LumenSysConfig>(
				LumenPackage.eINSTANCE, LumenConfig.class, "/META-INF/hotel.LumenSysConfig.xmi").get();
	}
	
	@Bean
	public FacebookChannel facebookChannel() {
		return new FacebookChannel();
	}
	
	@Bean
	public RelEx relex() {
		RelEx relex = new RelEx();
		relex.setDictionary(ImmutableMap.<String, String>of());
		
//		relex.loadTranslations();
		relex.loadLexRules(RelEx.class, "lumen.LexRules.xmi");
		relex.loadRelationRules(RelEx.class, "lumen.RelationRules.xmi");
		return relex;
	}

}
