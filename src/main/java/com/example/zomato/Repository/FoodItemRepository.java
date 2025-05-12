package com.example.zomato.Repository;

import com.example.zomato.Entity.FoodItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItemEntity, Long> {
}
