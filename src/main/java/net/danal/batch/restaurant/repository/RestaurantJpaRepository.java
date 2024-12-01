package net.danal.batch.restaurant.repository;

import net.danal.batch.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Long> {
}