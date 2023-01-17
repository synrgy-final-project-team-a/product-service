package com.synergy.productService.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "room_facility")
public class RoomFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_facility_id", nullable = false)
    private Long id;

    @Column(name = "ac")
    private Boolean ac;

    @Column(name = "table_learning")
    private Boolean tableLearning;

    @Column(name = "springbed")
    private Boolean springbed;

    @Column(name = "furniture")
    private Boolean furniture;

    @Column(name = "fan")
    private Boolean fan;

    @Column(name = "mirror")
    private Boolean mirror;

    @Column(name = "blanket")
    private Boolean blanket;

    @Column(name = "pillow")
    private Boolean pillow;

    @OneToOne(mappedBy = "roomFacility")
    private Facility facility;
}