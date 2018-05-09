package com.framgia.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RedisJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		new UpdateDataRedisTask().reloadData();
	}
}
