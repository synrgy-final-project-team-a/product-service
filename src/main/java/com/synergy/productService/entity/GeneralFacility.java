package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "general_facility")
@Where(clause = "deleted_at is null")

public class GeneralFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_facility_id")
    private Long id;

    @Column(name = "electric")
    private Boolean electric;

    @Column(name = "water")
    private Boolean water;

    @Column(name = "laundry")
    private Boolean laundry;

    @Column(name = "refrigerator")
    private Boolean refrigerator;

    @Column(name = "dispenser")
    private Boolean dispenser;

    @Column(name = "wifi")
    private Boolean wifi;

    @Column(name = "inside_bathroom")
    private Boolean insideBathroom;

    @Lob
    @Column(name = "size_room")
    private String sizeRoom;

    @OneToOne(mappedBy = "generalFacility")
    private Facility facility;

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @Column(name = "deleted_at")
    private LocalTime deletedAt;

}