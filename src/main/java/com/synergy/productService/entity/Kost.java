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

    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Lob
    @Column(name = "kost_type", nullable = false)
    private String kostType;

    @Column(name = "description", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Lob
    @Column(name = "pic", nullable = false)
    private String pic;

    @Lob
    @Column(name = "pic_phone_number", nullable = false)
    private String picPhoneNumber;

    @Column(name = "additional_notes", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String additionalNotes;

    @Lob
    @Column(name = "front_building_photo")
    private String frontBuildingPhoto;

    @Lob
    @Column(name = "front_road_photo")
    private String frontRoadPhoto;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kost_photo_id", nullable = false)
    private KostPhoto kostPhoto;
=======
    @Lob
    @Column(name = "front_farbuilding_photo")
    private String frontFarbuildingPhoto;

    @Lob
    @Column(name = "province")
    private String province;

    @Lob
    @Column(name = "city")
    private String city;
>>>>>>> master

    @Lob
    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "gmaps")
    private String gmaps;

    @Column(name = "location_additional_notes")
    @Type(type = "org.hibernate.type.TextType")
    private String locationAdditionalNotes;

    @ManyToMany(targetEntity = Room.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "kost_room",
            joinColumns = {
                    @JoinColumn(name = "kost_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "room_id")
            }
    )
    private List<Room> roomList = new ArrayList<>();

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @Column(name = "deleted_at")
    private LocalTime deletedAt;

    @Column(name = "enabled")
    private Boolean enabled;

}