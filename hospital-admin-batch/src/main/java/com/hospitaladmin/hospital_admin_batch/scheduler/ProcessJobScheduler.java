package com.hospitaladmin.hospital_admin_batch.scheduler;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.job.parameters.InvalidJobParametersException;
import org.springframework.batch.core.launch.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.launch.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessJobScheduler {

	private final JobOperator jobOperator;
	private final Job processJob;

	public ProcessJobScheduler(JobOperator jobOperator, Job processJob) {
		this.jobOperator = jobOperator;
		this.processJob = processJob;
	}

	@Scheduled(cron = "${app.batch.process-job.cron}")
	public void runProcessJob() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("scheduledAt", System.currentTimeMillis())
				.toJobParameters();

		JobExecution execution;
		try {
			execution = jobOperator.start(processJob, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException | InvalidJobParametersException exception) {
			throw new IllegalStateException("Failed to launch process job", exception);
		}
		if (execution.getStatus().isUnsuccessful()) {
			throw new IllegalStateException(
					"Process job finished with status " + execution.getStatus());
		}
	}
}
