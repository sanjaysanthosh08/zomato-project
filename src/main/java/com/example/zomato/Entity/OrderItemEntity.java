package com.example.zomato.Entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id") // Foreign key column
    @JsonBackReference // Matches @JsonManagedReference in OrderEntity
    private OrderEntity order;
}
