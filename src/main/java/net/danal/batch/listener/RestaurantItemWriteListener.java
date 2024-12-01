package net.danal.batch.listener;

import lombok.extern.slf4j.Slf4j;
import net.danal.batch.restaurant.domain.RestaurantDto;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

@Slf4j
public class RestaurantItemWriteListener implements ItemWriteListener<RestaurantDto> {

    @Override
    public void beforeWrite(Chunk<? extends RestaurantDto> items) {
        log.info("Writing started persons list : {}", items.toString());
    }

    @Override
    public void afterWrite(Chunk<? extends RestaurantDto> items) {
        log.info("Writing completed persons list : {}", items.toString());
        ;
    }

    @Override
    public void onWriteError(Exception e, Chunk<? extends RestaurantDto> items) {
        log.error("Error in reading the person records : {}", items.toString());
        log.error("Error in reading the person records : {}", e.getMessage());
    }
}
