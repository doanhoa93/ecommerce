package com.framgia.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class RedisJob extends QuartzJobBean {

	private UpdateDataTask updateDataTask;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		updateDataTask.updateData();
	}

	public UpdateDataTask getUpdateDataTask() {
		return updateDataTask;
	}

	public void setUpdateDataTask(UpdateDataTask updateDataTask) {
		this.updateDataTask = updateDataTask;
	}
}
