package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "facility")
@Where(clause = "deleted_at is null")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id", nullable = false)
    private Long id;


    @Column(name = "ac")
    private Boolean ac;

    @Column(name = "window")
    private Boolean window;

    @Column(name = "tv")
    private Boolean tv;
    @Column(name = "table_learning")
    private Boolean tableLearning;
    @Column(name = "springbed")
    private Boolean springbed;

    @Column(name = "furniture")
    private Boolean furniture;

    @Column(name = "fan")
    private Boolean fan;

    @Column(name = "blanket")
    private Boolean blanket;

    @Column(name = "sitting_closet")
    private Boolean sittingCloset;

    @Column(name = "non_sitting_closet")
    private Boolean nonSittingCloset;

    @Column(name = "water_heater")
    private Boolean waterHeater;

    @Column(name = "shower")
    private Boolean shower;

    @Column(name = "inisde_bathroom")
    private Boolean insideBathroom;

    @Column(name = "outside_bathroom")
    private Boolean outsideBathroom;

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @Column(name = "deleted_at")
    private LocalTime deletedAt;
}
