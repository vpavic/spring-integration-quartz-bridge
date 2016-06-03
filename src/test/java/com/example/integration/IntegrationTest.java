package com.example.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class IntegrationTest {

	private static final int MESSAGE_COUNT = 10;

	@Autowired
	private MessageGateway gateway;

	@Autowired
	private Consumer consumer;

	@Test
	public void test() throws Exception {
		for (int i = 0; i < MESSAGE_COUNT; i++) {
			this.gateway.sendMessage("message-" + i);
		}
		Thread.sleep(MESSAGE_COUNT * 1000);
		assertThat(this.consumer.consumedCount(), equalTo(MESSAGE_COUNT));
	}

}
