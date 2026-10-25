package com.hospitaladmin.hospital_admin_batch.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ProcessTasklet implements Tasklet {

	private static final Logger logger = LoggerFactory.getLogger(ProcessTasklet.class);

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		logger.info("Process job tasklet executed");
		return RepeatStatus.FINISHED;
	}
}
