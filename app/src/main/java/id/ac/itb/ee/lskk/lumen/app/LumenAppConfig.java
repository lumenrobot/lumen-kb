package id.ac.itb.ee.lskk.lumen.app;

import id.ac.itb.ee.lskk.lumen.core.FacebookChannel;
import id.ac.itb.ee.lskk.lumen.core.FacebookChannel.FacebookAction;
import id.ac.itb.ee.lskk.lumen.core.FacebookChannel.FacebookActionResult;
import id.ac.itb.ee.lskk.lumen.core.FacebookChannel.FacebookPerception;
import id.ac.itb.ee.lskk.lumen.core.impl.LumenConfig;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author ceefour
 */
@Configuration
@Import(LumenConfig.class)
@EnableScheduling
public class LumenAppConfig {
	
	private static final Logger log = LoggerFactory
			.getLogger(LumenAppConfig.class);
	
	@Inject
	private FacebookChannel facebookChannel;
	
	@Scheduled(cron="*/60 * * * * *")
	public void pollPerceiveRespond() {
		log.info("Executing poll-perceive-respond...");
		
		List<FacebookPerception> perceptions = facebookChannel.poll();
		if (!perceptions.isEmpty()) {
			log.info("{} perceptions: {}", perceptions.size(), perceptions);
		}
			
		List<FacebookAction> actions = facebookChannel.perceive(perceptions);
		if (!actions.isEmpty()) {
			log.info("{} actions: {}", actions.size(), actions);
		}
	
		List<FacebookActionResult> results = facebookChannel.respond(actions);
		if (!results.isEmpty()) {
			log.info("{} actionResults: {}", results.size(), results);
		}
	}

}
