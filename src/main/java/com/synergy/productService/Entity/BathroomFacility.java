package com.synergy.productService.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bathroom_facility")
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

}