package com.framgia.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class EmailJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		new EmailTask().sendEmail();
	}
}
