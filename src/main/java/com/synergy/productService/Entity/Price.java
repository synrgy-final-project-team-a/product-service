package com.synergy.productService.Entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id", nullable = false)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "additional_amount")
    private Double additionalAmount;

    @Column(name = "duration_type")
    private String durationType;

}