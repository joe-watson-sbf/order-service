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
    private Instant createdOn = Instant.now();
    @Transient
    private BigDecimal totalPrice;

}
