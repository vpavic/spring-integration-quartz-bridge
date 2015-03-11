package hr.kapsch.scheduling.quartz;

import hr.kapsch.integration.PollingEndpointQuartzBridgeTrigger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.endpoint.AbstractPollingEndpoint;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PollingEndpointQuartzBridgeJob extends ApplicationContextAwareQuartzJobBean {

	public static final String POLLING_ENDPOINT_BEAN_NAME_KEY = "pollingEndpointBeanName";
	public static final String TRIGGER_BEAN_NAME_KEY = "triggerBeanName";

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String pollingEndpointName = (String) context.getMergedJobDataMap().get(POLLING_ENDPOINT_BEAN_NAME_KEY);
		String triggerName = (String) context.getMergedJobDataMap().get(TRIGGER_BEAN_NAME_KEY);

		ApplicationContext ctx = getApplicationContext(context);
		AbstractPollingEndpoint endpoint = ctx.getBean(pollingEndpointName, AbstractPollingEndpoint.class);
		PollingEndpointQuartzBridgeTrigger trigger = ctx.getBean(triggerName, PollingEndpointQuartzBridgeTrigger.class);

		endpoint.stop();
		trigger.reset();
		endpoint.start();
	}

}
