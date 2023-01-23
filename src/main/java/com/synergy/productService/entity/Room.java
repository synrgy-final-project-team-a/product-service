package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "room")
@Where(clause = "deleted_at is null")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long id;


    @Column(name = "front_room_photo")
    private String frontRoomPhoto;


    @Column(name = "inside_room_photo")
    private String insideRoomPhoto;


    @Column(name = "bathroom_photo")
    private String bathroomPhoto;


    @Column(name = "other_room_photo")
    private String otherRoomPhoto;

    @Column(name = "quantity_room")
    private Integer quantityRoom;

    @Column(name = "available_room")
    private Integer availableRoom;

    @Column(name = "facility")
    private String facility;
    @Column(name = "kost_type_man")
    private Boolean kostTypeMan;

    @Column(name = "kost_type_woman")
    private Boolean kostTypeWoman;

    @Column(name = "kost_type_mixed")
    private Boolean kostTypeMixed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "price_id", nullable = false)
    private Price price;

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @Column(name = "deleted_at")
    private LocalTime deletedAt;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToOne(targetEntity = Facility.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "facility_id")
    private Facility facilityId;
}