package com.framgia.job;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/*Install redis server
https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-redis-on-ubuntu-16-04
*/

public class MainJob {

	public void run() throws SchedulerException {
		try {
			Runtime.getRuntime().exec("/bin/bash -c ~/elasticsearch-1.5.2/bin/./elasticsearch");
		} catch (IOException e) {
			e.printStackTrace();
		}

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		JobDetail redisJob = newJob(RedisJob.class).withIdentity("redisJob", "groupRedis").build();
		Trigger redisTrigger = newTrigger().withIdentity("triggerRedisJob", "groupRedis")
		    .withSchedule(CronScheduleBuilder.cronSchedule("0 15 10 ? * MON-FRI")).build();
		sched.scheduleJob(redisJob, redisTrigger);

		JobDetail emailJob = newJob(EmailJob.class).withIdentity("emailJob", "groupEmail").build();
		Trigger emailTrigger = newTrigger().withIdentity("triggerEmailJob", "groupEmail")
		    .withSchedule(CronScheduleBuilder.cronSchedule("5 * * * * ? *")).build();
		sched.scheduleJob(emailJob, emailTrigger);

		sched.start();
	}

	public static void main(String[] args) throws SchedulerException {
		MainJob mainJob = new MainJob();
		mainJob.run();
	}
}
