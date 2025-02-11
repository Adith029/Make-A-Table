package com.maverix.makeatable.repositories;

import com.maverix.makeatable.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Restaurant findByFullName(String fullName);

    @Query("SELECT r FROM Restaurant r ORDER BY (SELECT AVG(rr.rating) FROM r.ratings rr) DESC")
    List<Restaurant> findTop5ByOrderByAverageRatingDesc();

    Optional<Restaurant> findByUser_Id(Long userId);

    Restaurant findByUserId(Long userId);
}
