package net.danal.batch.listener;

import lombok.extern.slf4j.Slf4j;
import net.danal.batch.restaurant.domain.RestaurantDto;
import org.springframework.batch.core.ItemReadListener;

@Slf4j
public class RestaurantItemReadListener implements ItemReadListener<RestaurantDto> {

    @Override
    public void beforeRead() {
        log.info("Reading A New Restaurant Record");
    }

    @Override
    public void afterRead(RestaurantDto dto) {
        log.info("New Restaurant Record Read : {}", dto.toString());
    }

    @Override
    public void onReadError(Exception e) {
        log.error("Error In Reading The Restaurant Record {}: ", e.toString());
    }
}
