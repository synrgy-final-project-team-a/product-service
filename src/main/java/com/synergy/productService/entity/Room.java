package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Type(type = "org.hibernate.type.TextType")
    private String frontRoomPhoto;


    @Column(name = "inside_room_photo")
    @Type(type = "org.hibernate.type.TextType")
    private String insideRoomPhoto;


    @Column(name = "bathroom_photo")
    @Type(type = "org.hibernate.type.TextType")
    private String bathroomPhoto;


    @Column(name = "other_room_photo")
    @Type(type = "org.hibernate.type.TextType")
    private String otherRoomPhoto;

    @Column(name = "quantity_room")
    private Integer quantityRoom;

    @Column(name = "kost_type_man")
    private Boolean kostTypeMan;

    @Column(name = "kost_type_woman")
    private Boolean kostTypeWoman;

    @Column(name = "kost_type_mixed")
    private Boolean kostTypeMixed;

    @Column(name = "size_room")
    private String sizeRoom;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "available_room")
    private Integer availableRoom;

    @OneToOne(targetEntity = Facility.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kost_id")
    private Kost kost;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}