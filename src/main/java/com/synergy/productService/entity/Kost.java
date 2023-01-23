package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "kost")
@Where(clause = "deleted_at is null")
public class Kost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kost_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "description", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "pic", nullable = false)
    private String pic;

    @Column(name = "pic_phone_number", nullable = false)
    private String picPhoneNumber;

    @Column(name = "additional_notes", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String additionalNotes;

    @Column(name = "front_building_photo")
    private String frontBuildingPhoto;

    @Column(name = "front_road_photo")
    private String frontRoadPhoto;

    @Column(name = "front_farbuilding_photo")
    private String frontFarbuildingPhoto;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "gmaps")
    private String gmaps;

    @Column(name = "location_additional_notes")
    @Type(type = "org.hibernate.type.TextType")
    private String locationAdditionalNotes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rule_id", nullable = false)
    private Rule rule;

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @Column(name = "deleted_at")
    private LocalTime deletedAt;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "electric")
    private Boolean electric;
    @Column(name = "water")
    private Boolean water;

    @Column(name = "wifi")
    private Boolean wifi;

    @Column(name = "laundry")
    private Boolean laundry;
    @Column(name = "refrigerator")
    private Boolean refrigerator;
    @Column(name = "dispenser")
    private Boolean dispenser;
    @Column(name = "size_room")
    private String sizeRoom;
    @Column(name = "inside_bathroom")
    private Boolean insideBathroom;

}