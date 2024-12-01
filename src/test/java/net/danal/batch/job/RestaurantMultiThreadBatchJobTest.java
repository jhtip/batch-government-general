package net.danal.batch.job;

import net.danal.batch.TestBatchConfig;
import net.danal.batch.restaurant.repository.RestaurantJdbcRepository;
import net.danal.batch.restaurant.repository.RestaurantJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles(value = "local")
@SpringBatchTest
@SpringBootTest(classes = {RestaurantJdbcRepository.class, TestBatchConfig.class, JobStepRestaurantConfiguration.class})
class RestaurantMultiThreadBatchJobTest {

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    @Qualifier("RESTAURANT_CSV_FLAT_FILE_CHUNK_JOB")
    Job restaurantFlatFileConfigurationJob;

    @BeforeEach
    void beforeEach() {
        jobLauncherTestUtils.setJob(restaurantFlatFileConfigurationJob);
    }

    @AfterEach
    void after() {
        System.out.println("AFTER.. Restaurant Data Delete All!!");
        restaurantJpaRepository.deleteAll();
    }

    @Test
    public void jdbcTemplate_multi_thread_test() throws Exception {
        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        //then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

}

