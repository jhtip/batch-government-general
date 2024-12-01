package net.danal.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.danal.batch.listener.*;
import net.danal.batch.processor.JobRestaurantFlatFileItemProcessor;
import net.danal.batch.reader.JobRestaurantFlatFileItemReader;
import net.danal.batch.restaurant.domain.RestaurantDto;
import net.danal.batch.restaurant.repository.RestaurantJdbcRepository;
import net.danal.batch.writer.JobRestaurantFlatFileItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.batch.item.support.builder.SynchronizedItemStreamReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
//@ConditionalOnProperty(prefix = "spring.batch.job", name = "restaurant", havingValue = JobStepRestaurantConfiguration.JOB_NAME)
public class JobStepRestaurantConfiguration {

    private static final int CHUNK_SIZE = 10;

    public static final String JOB_NAME = "RESTAURANT_CSV_FLAT_FILE_CHUNK";

    private final RestaurantJdbcRepository restaurantJdbcRepository;

    private final JobRepository jobRepository;

    private final TransactionManager transactionManager;

    private final JobRestaurantFlatFileItemReader jobRestaurantFlatFileItemReader;

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        var taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(10);
        //taskExecutor.setQueueCapacity(20);
        taskExecutor.setThreadNamePrefix("batch-thread-");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean(name = JOB_NAME + "_STEP")
    @JobScope
    public Step restaurantFlatFileChunkConfigurationStep() throws Exception {
        return new StepBuilder(JOB_NAME, jobRepository)
                .<RestaurantDto, RestaurantDto>chunk(CHUNK_SIZE, (PlatformTransactionManager) transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(new ChunkRestaurantListener())
                .listener(new RestaurantItemSkipListener())
                .listener(new RestaurantItemReadListener())
                .listener(new RestaurantItemWriteListener())
                .taskExecutor(taskExecutor())
                .transactionManager((PlatformTransactionManager) transactionManager)
                .build();
    }

    @Bean(name = JOB_NAME + "_JOB")
    public Job restaurantFlatFileConfigurationJob() throws Exception {
        return new JobBuilder(JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(restaurantFlatFileChunkConfigurationStep())
                .listener(new JobRestaurantExecutionListener())
                .listener(new StepRestaurantExecutionListener())
                .build();
    }

    @Bean(name = JOB_NAME + "_READER")
    @StepScope
    public SynchronizedItemStreamReader<RestaurantDto> reader() throws Exception {
        return new SynchronizedItemStreamReaderBuilder<RestaurantDto>()
                .delegate(jobRestaurantFlatFileItemReader.csvFlatFileItemReader())
                .build();
    }

    @Bean(name = JOB_NAME + "_PROCESSOR")
    @StepScope
    public ItemProcessor<RestaurantDto, RestaurantDto> processor() {
        return new JobRestaurantFlatFileItemProcessor();
    }

    @Bean(name = JOB_NAME + "_WRITER")
    @StepScope
    public ItemWriter<RestaurantDto> writer() {
        return new JobRestaurantFlatFileItemWriter(restaurantJdbcRepository);
    }

}
