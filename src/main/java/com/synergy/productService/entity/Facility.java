package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(name = "blanket")
    private Boolean blanket;
    @Column(name = "fan")
    private Boolean fan;
    @Column(name = "furniture")
    private Boolean furniture;
    @Column(name = "shower")
    private Boolean shower;
    @Column(name = "sitting_closet")
    private Boolean sittingCloset;
    @Column(name = "springbed")
    private Boolean springBed;
    @Column(name = "table_learning")
    private Boolean tableLearning;
    @Column(name = "water_heater")
    private Boolean waterHeater;
    @Column(name = "inside_bathroom")
    private Boolean insideBathroom;
    @Column(name = "non_sitting_closet")
    private Boolean nonSittingCloset;
    @Column(name = "outside_bathroom")
    private Boolean outsideBathroom;
    @Column(name = "windows")
    private Boolean windows;
    @Column(name = "room_tv")
    private Boolean roomTv;
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
