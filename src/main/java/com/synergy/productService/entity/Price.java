package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "price")
@Where(clause = "deleted_at is null")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id", nullable = false)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "duration_type")
    private String durationType;

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @Column(name = "deleted_at")
    private LocalTime deletedAt;

}