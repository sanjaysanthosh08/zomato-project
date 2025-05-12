package com.example.zomato.Service;

import com.example.zomato.Entity.FoodItemEntity;
import com.example.zomato.Repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    public List<FoodItemEntity> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public FoodItemEntity saveFoodItem(FoodItemEntity foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }

    public FoodItemEntity updateFoodItem(Long id, FoodItemEntity updatedItem) {
        return foodItemRepository.findById(id).map(item -> {
            item.setName(updatedItem.getName());
            item.setImageUrl(updatedItem.getImageUrl());
            item.setPrice(updatedItem.getPrice());
            return foodItemRepository.save(item);
        }).orElse(null);
    }
}
