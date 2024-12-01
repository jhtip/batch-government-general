package net.danal.batch.processor;

import lombok.extern.slf4j.Slf4j;
import net.danal.batch.restaurant.domain.RestaurantDto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class JobRestaurantFlatFileItemProcessor implements ItemProcessor<RestaurantDto, RestaurantDto> {
    
    @Override
    public RestaurantDto process(RestaurantDto item) {
        if (item.getNumber() == null) {
            return null;
        }
        return item;
    }
}
