package com.joedev.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NonNull
    private Long clientId;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = OrderDetails.class)
    private List<OrderDetails> orderDetails;
    private Instant createdOn;
    @Transient
    private BigDecimal totalPrice = new BigDecimal(0);;

    public Order(@NonNull Long clientId, List<OrderDetails> orderDetails) {
        this.clientId = clientId;
        this.orderDetails = orderDetails;
        this.createdOn  = Instant.now();
    }

    public BigDecimal getTotalPrice() {
        orderDetails
                .forEach(orderDetails1 ->
                        this.setTotalPrice(
                                this.totalPrice.add(orderDetails1.getAmountTotal())));
        return totalPrice;
    }
}
