package net.danal.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class StepRestaurantExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("StepStartedAt : {}", stepExecution.getStartTime());
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("StepFinishedAt : {}", stepExecution.getEndTime());
        return null;
    }
}
