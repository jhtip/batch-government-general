package net.danal.batch.writer;

import net.danal.batch.restaurant.domain.Restaurant;
import net.danal.batch.restaurant.repository.RestaurantJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@AutoConfigureTestDatabase(replace = NONE)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ActiveProfiles("test")
public class RestaurantJpaRepositoryTest {

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Test
    void insertAndSelectCountRestaurant() {
        // given
        int totalCount = restaurantJpaRepository.findAll().size();
        Restaurant restaurant = Restaurant.builder()
                .business_name("다날식당")
                .build();
        // when
        restaurantJpaRepository.save(restaurant);
        // then
        assertEquals(restaurantJpaRepository.count(), totalCount + 1);
    }

}
