package com.synergy.productService.Entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @Lob
    @Column(name = "province")
    private String province;

    @Lob
    @Column(name = "city")
    private String city;

    @Lob
    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "gmaps")
    private String gmaps;

    @Column(name = "additional_notes")
    @Type(type = "org.hibernate.type.TextType")
    private String additionalNotes;

}