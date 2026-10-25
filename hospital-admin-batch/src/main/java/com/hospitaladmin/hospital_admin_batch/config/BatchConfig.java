package com.hospitaladmin.hospital_admin_batch.config;

import com.hospitaladmin.hospital_admin_batch.writer.ProcessTasklet;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

	@Bean
	public Job processJob(JobRepository jobRepository, Step processStep) {
		return new JobBuilder("processJob", jobRepository)
				.start(processStep)
				.build();
	}

	@Bean
	public Step processStep(
			JobRepository jobRepository,
			PlatformTransactionManager transactionManager,
			ProcessTasklet processTasklet) {
		return new StepBuilder("processStep", jobRepository)
				.tasklet(processTasklet, transactionManager)
				.build();
	}
}
