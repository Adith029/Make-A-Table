package com.maverix.makeatable.controllers;

import com.maverix.makeatable.dto.Restaurent.RestaurantGetDto;
import com.maverix.makeatable.services.RestaurantService;
import com.maverix.makeatable.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private final RestaurantService restaurantService;

    public AdminController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    @GetMapping
    public ResponseEntity<Response<List<RestaurantGetDto>>> getAllRestaurants() {
        List<RestaurantGetDto> restaurants = restaurantService.getAllRestaurants();
        Response<List<RestaurantGetDto>> response = Response.<List<RestaurantGetDto>>builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .message("Restaurants retrieved successfully")
                .data(restaurants)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("approve/{id}")
    public ResponseEntity<Response<RestaurantGetDto>> approveRestaurant(@PathVariable Long id) {
        RestaurantGetDto updatedRestaurant= restaurantService.approveRestaurant(id);
        Response<RestaurantGetDto> response = Response.<RestaurantGetDto>builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .message("Restaurant Approved successfully")
                .data(updatedRestaurant)
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping("decline/{id}")
    public ResponseEntity<Response<RestaurantGetDto>> declineRestaurant(@PathVariable Long id) {
        RestaurantGetDto updatedRestaurant= restaurantService.declineRestaurant(id);
        Response<RestaurantGetDto> response = Response.<RestaurantGetDto>builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .message("Restaurant Declined  successfully")
                .data(updatedRestaurant)
                .build();
        return ResponseEntity.ok(response);
    }
}
