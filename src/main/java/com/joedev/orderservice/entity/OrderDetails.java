package com.joedev.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToOne(targetEntity = Product.class)
    private Product product;
    @NonNull
    private Integer quantity;

    public BigDecimal getAmountTotal() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}
