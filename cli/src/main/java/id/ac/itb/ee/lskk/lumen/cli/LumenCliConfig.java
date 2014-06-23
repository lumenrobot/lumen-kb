package id.ac.itb.ee.lskk.lumen.cli;

import id.ac.itb.ee.lskk.relexid.core.RelExConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ceefour
 *
 */
@Configuration
@Import(RelExConfig.class)
public class LumenCliConfig {
	
	@Bean
	public LumenCli lumenCli() {
		return new LumenCli();
	}

}
