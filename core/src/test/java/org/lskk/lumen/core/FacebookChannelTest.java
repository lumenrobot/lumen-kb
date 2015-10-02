package org.lskk.lumen.core;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=LumenConfig.class)
public class FacebookChannelTest {

	private static final Logger log = LoggerFactory
			.getLogger(FacebookChannelTest.class);
	
	@Inject
	private FacebookChannel facebookChannel;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void poll() {
		List<FacebookChannel.FacebookPerception> perceptions = facebookChannel.poll();
		log.info("{} perceptions: {}", perceptions.size(), perceptions);
	}

	@Test
	public void perceive() {
		ImmutableList<FacebookChannel.FacebookPerception> perceptions = ImmutableList.of(
				new FacebookChannel.FacebookPerception(null, 10152513803701672l, "Hendy Irawan", "aku ingin unta kuning", "1428312064109783_1428380687436254"));
		List<FacebookChannel.FacebookAction> actions = facebookChannel.perceiveRelex(perceptions);
		log.info("{} actions: {}", actions.size(), actions);
	}

	@Test
	public void action() {
		ImmutableList<FacebookChannel.FacebookAction> actions = ImmutableList.of(
				new FacebookChannel.FacebookAction(null, "1428312064109783_1428380687436254",
						"@[10152513803701672:1:Hendy Irawan] :"
						+ "\n\nStructure:"
						+ "\n(S (PP i) (VP want-v (NP (SP yellow-s) camel-n)) )"
						+ "\n\nIn English:"
						+ "\nI want yellow camel"
						+ "\n\nIn Indonesian:"
						+ "\nAku hendak unta kekuning-kuningan",
						10152513803701672l, "Hendy Irawan"));
		List<FacebookChannel.FacebookActionResult> results = facebookChannel.respond(actions);
		log.info("{} actionResults: {}", results.size(), results);
	}
	
	@Test
	public void pollPerceiveRespond() {
		List<FacebookChannel.FacebookPerception> perceptions = facebookChannel.poll();
		log.info("{} perceptions: {}", perceptions.size(), perceptions);
		
		List<FacebookChannel.FacebookAction> actions = facebookChannel.perceiveRelex(perceptions);
		log.info("{} actions: {}", actions.size(), actions);

		List<FacebookChannel.FacebookActionResult> results = facebookChannel.respond(actions);
		log.info("{} actionResults: {}", results.size(), results);
	}

}
