package com.synergy.productService.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "kost")
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "quantity_room", nullable = false)
    private Integer quantityRoom;

    @Column(name = "available_room", nullable = false)
    private Integer availableRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kost_photo_id", nullable = false)
    private KostPhoto kostPhoto;

    @ManyToMany(targetEntity = Facility.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "kost_facility",
            joinColumns = {
                    @JoinColumn(name = "kost_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "facility_id")
            }
    )
    private List<Facility> facilityList = new ArrayList<>();
}