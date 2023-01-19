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

    @Lob
    @Column(name = "front_room_photo")
    private String frontRoomPhoto;

    @Lob
    @Column(name = "inside_room_photo")
    private String insideRoomPhoto;

    @Lob
    @Column(name = "bathroom_photo")
    private String bathroomPhoto;

    @Lob
    @Column(name = "other_room_photo")
    private String otherRoomPhoto;

    @Column(name = "quantity_room")
    private Integer quantityRoom;

    @Column(name = "available_room")
    private Integer availableRoom;

    @Column(name = "facility")
    private String facility;
    @Lob
    @Column(name = "kost_type")
    private String kostType;

    @ManyToMany(targetEntity = Price.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "room_price",
            joinColumns = {
                    @JoinColumn(name = "room_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "price_id")
            }
    )
    private List<Price> priceList = new ArrayList<>();

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @Column(name = "deleted_at")
    private LocalTime deletedAt;

    @Column(name = "enabled")
    private Boolean enabled;
}