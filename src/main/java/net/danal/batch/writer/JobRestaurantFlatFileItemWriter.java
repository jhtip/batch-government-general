package net.danal.batch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.danal.batch.restaurant.domain.RestaurantDto;
import net.danal.batch.restaurant.repository.RestaurantJdbcRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobRestaurantFlatFileItemWriter implements ItemWriter<RestaurantDto> {

    private final RestaurantJdbcRepository restaurantJdbcRepository;

    @SuppressWarnings("NullableProblems")
    @Override
    public void write(Chunk<? extends RestaurantDto> chunk) {
        this.restaurantJdbcRepository.write(chunk);
    }
}
