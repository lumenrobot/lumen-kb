package id.ac.itb.ee.lskk.lumen.core.impl;

import id.ac.itb.ee.lskk.lumen.core.FacebookChannel;
import id.ac.itb.ee.lskk.lumen.core.LumenPackage;
import id.ac.itb.ee.lskk.lumen.core.LumenSysConfig;
import id.ac.itb.ee.lskk.relexid.core.RelExConfig;

import org.soluvas.commons.OnDemandXmiLoader;
import org.soluvas.commons.config.CommonsWebConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author ceefour
 */
@Configuration
@Import({CommonsWebConfig.class, RelExConfig.class})
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
	
}
