package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "kost_photo")
@Where(clause = "deleted_at is null")
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

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @Column(name = "deleted_at")
    private LocalTime deletedAt;

}