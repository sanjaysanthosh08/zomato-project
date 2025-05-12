package com.example.zomato.Controller;

import com.example.zomato.Entity.FoodItemEntity;
import com.example.zomato.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping("/foods")
    public List<FoodItemEntity> getAllFoods() {
        return foodItemService.getAllFoodItems();
    }

    @PostMapping("/foods")
    public FoodItemEntity addFood(@RequestBody FoodItemEntity foodItem) {
        return foodItemService.saveFoodItem(foodItem);
    }

    @PutMapping("/foods/{id}")
    public FoodItemEntity updateFood(@PathVariable Long id, @RequestBody FoodItemEntity updatedItem) {
        return foodItemService.updateFoodItem(id, updatedItem);
    }

    @DeleteMapping("/foods/{id}")
    public void deleteFood(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
    }
}
