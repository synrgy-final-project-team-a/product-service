package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "bathroom_facility")
@Where(clause = "deleted_at is null")
public class BathroomFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bathroom_facility_id", nullable = false)
    private Long id;

    @Column(name = "bathtub")
    private Boolean bathtub;

    @Column(name = "shower")
    private Boolean shower;

    @Column(name = "sitting_closet")
    private Boolean sittingCloset;

    @Column(name = "water_heater")
    private Boolean waterHeater;

    @OneToOne(mappedBy = "bathroomFacility")
    private Facility facility;

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @Column(name = "deleted_at")
    private LocalTime deletedAt;

    JSONOb

}