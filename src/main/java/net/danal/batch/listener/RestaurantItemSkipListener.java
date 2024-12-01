package net.danal.batch.listener;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.danal.batch.restaurant.domain.RestaurantDto;
import org.springframework.batch.core.SkipListener;

@Getter
@Slf4j
public class RestaurantItemSkipListener implements SkipListener<RestaurantDto, RestaurantDto> {

    private int skipCount = 0;

    @Override
    public void onSkipInRead(Throwable t) {
        log.info("Skipped during reading : {}", t.getMessage());
        skipCount++;
    }

    @Override
    public void onSkipInWrite(RestaurantDto item, Throwable t) {
        log.info("Skipped during writing : {} ", item.toString());
        log.info("Error : {}", t.getMessage());
        skipCount++;
    }

    @Override
    public void onSkipInProcess(RestaurantDto item, Throwable t) {
        log.info("Skipped during processing : {}", item.toString());
        log.info("Error : {}", t.getMessage());
        skipCount++;
    }

}
