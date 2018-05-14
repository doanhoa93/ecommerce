package com.framgia.job;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import redis.clients.jedis.Jedis;

public class MainJob {
	public void run() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		JobDetail job = newJob(RedisJob.class).withIdentity("redisJob", "groupRedis").build();
		Trigger trigger = newTrigger().withIdentity("triggerRedisJob", "groupRedis")
		    .withSchedule(CronScheduleBuilder.cronSchedule("0 15 10 ? * MON-FRI")).build();
		sched.scheduleJob(job, trigger);

		JobDetail emailJob = newJob(EmailJob.class).withIdentity("emailJob", "groupEmail").build();
		Trigger emailTrigger = newTrigger().withIdentity("triggerEmailJob", "groupEmail")
		    .withSchedule(CronScheduleBuilder.cronSchedule("5 * * * * ? *")).build();
		sched.scheduleJob(emailJob, emailTrigger);

		sched.start();
	}

	public static void main(String[] args) throws SchedulerException {
		Jedis jedis = new Jedis();
		jedis.flushAll();
		jedis.close();
		MainJob mainJob = new MainJob();
		mainJob.run();
	}
}
