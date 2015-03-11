package hr.kapsch.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

	private static final int MESSAGE_COUNT = 10;

	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("test-context.xml");
		MessageGateway gateway = ctx.getBean(MessageGateway.class);
		for (int i = 0; i < MESSAGE_COUNT; i++) {
			String message = "message" + i;
			LOGGER.info("sent {}", message);
			gateway.sendMessage(message);
		}
		Thread.sleep(MESSAGE_COUNT * 1000);
		ctx.close();
	}

	public void consumeMessage(String message) {
		LOGGER.info("consumed {}", message);
	}

}
