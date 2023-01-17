package com.synergy.productService.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "kost_photo")
public class KostPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kost_photo_id", nullable = false)
    private Long kostPhotoId;

    @Lob
    @Column(name = "front_building_photo")
    private String frontBuildingPhoto;

    @Lob
    @Column(name = "front_road_photo")
    private String frontRoadPhoto;

    @Lob
    @Column(name = "front_farbuilding_photo")
    private String frontFarbuildingPhoto;
}