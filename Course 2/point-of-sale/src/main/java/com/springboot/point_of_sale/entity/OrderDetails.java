package com.springboot.point_of_sale.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {

    @Id
    @Column(name = "order_details_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderDetailsId;

    @Column(name = "qty",length = 100,nullable = false)
    private double qty;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    public OrderDetails(double qty, double unitPrice, Double amount, Item item, Order order) {
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.item = item;
        this.order = order;
    }
}
