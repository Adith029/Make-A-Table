package com.maverix.makeatable.repositories;

import com.maverix.makeatable.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
    Optional<Orders> findTopByCreatedByUserIdOrderByDateTimeDesc(Long userId);

    List<Orders> findByRestaurantId(Long restaurantId);

    List<Orders> findTop10ByOrderByDateTimeDesc();
}
