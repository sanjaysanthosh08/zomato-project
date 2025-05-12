package com.example.zomato.Controller;

import com.example.zomato.Entity.RestaurantEntity;
import com.example.zomato.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular frontend
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PostMapping("/restaurants")
    public RestaurantEntity addRestaurant(@RequestBody RestaurantEntity restaurant) {
        return restaurantService.saveRestaurant(restaurant);
    }

    // PUT endpoint to update an existing restaurant
    @PutMapping("/restaurants/{id}")
    public RestaurantEntity updateRestaurant(@PathVariable Long id, @RequestBody RestaurantEntity restaurant) {
        return restaurantService.updateRestaurant(id, restaurant);
    }

    // DELETE endpoint to remove a restaurant by ID
    @DeleteMapping("/restaurants/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }
}
