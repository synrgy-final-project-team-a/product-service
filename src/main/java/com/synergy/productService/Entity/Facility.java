package com.synergy.productService.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bathroom_facility_id", nullable = false)
    private BathroomFacility bathroomFacility;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "general_Facility_id", nullable = false)
    private GeneralFacility generalFacility;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_facility_id", nullable = false)
    private RoomFacility roomFacility;
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

    @Lob
    @Column(name = "kost_type")
    private String kostType;

    @ManyToMany(targetEntity = Price.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "facility_price",
            joinColumns = {
                    @JoinColumn(name = "facility_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "price_id")
            }
    )
    private List<Price> priceList = new ArrayList<>();

}