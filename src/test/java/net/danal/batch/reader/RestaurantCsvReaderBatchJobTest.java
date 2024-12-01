package net.danal.batch.reader;

import net.danal.batch.TestBatchConfig;
import net.danal.batch.job.JobStepRestaurantConfiguration;
import net.danal.batch.restaurant.repository.RestaurantJdbcRepository;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "local")
@SpringBatchTest
@SpringBootTest(classes = {RestaurantJdbcRepository.class, TestBatchConfig.class, JobStepRestaurantConfiguration.class})
class RestaurantCsvReaderBatchJobTest {

    @Autowired
    private RestaurantJdbcRepository restaurantJdbcRepository;

    @Autowired
    private JobRestaurantFlatFileItemReader jobRestaurantFlatFileItemReader;

    @Test
    public void jdbcTemplate_csv_read_test() throws Exception {
        //when
        var reader = jobRestaurantFlatFileItemReader.csvFlatFileItemReader();
        //then
        System.out.println("Read Count : " + reader.getCurrentItemCount());
    }

}

