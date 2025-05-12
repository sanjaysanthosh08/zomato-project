package com.example.zomato.Service;

import com.example.zomato.Entity.RestaurantEntity;
import com.example.zomato.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public RestaurantEntity saveRestaurant(RestaurantEntity restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Update a restaurant if it exists, else return null or throw an exception
    public RestaurantEntity updateRestaurant(Long id, RestaurantEntity restaurant) {
        Optional<RestaurantEntity> existingRestaurant = restaurantRepository.findById(id);
        if (existingRestaurant.isPresent()) {
            RestaurantEntity restaurantToUpdate = existingRestaurant.get();
            restaurantToUpdate.setName(restaurant.getName());
            restaurantToUpdate.setImage(restaurant.getImage());
            return restaurantRepository.save(restaurantToUpdate);
        } else {
            // Handle the case where the restaurant doesn't exist
            // You can throw an exception or return null
            return null;
        }
    }

    // Delete a restaurant by ID
    public void deleteRestaurant(Long id) {
        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(id);
        restaurant.ifPresent(restaurantRepository::delete);
    }
}
