package com.joedev.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private BigDecimal price;
    @NonNull
    private Integer quantity;

    @Transient
    private Boolean active;

    public Boolean getActive() {
        return this.quantity > 0;
    }
}
