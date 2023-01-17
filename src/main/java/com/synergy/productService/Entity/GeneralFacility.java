package com.synergy.productService.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "general_facility")
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

}