package com.synergy.productService.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @Column(name = "city")
    private String city;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "gmaps")
    private String gmaps;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "province")
    private String province;

    @Lob
    @Column(name = "gender")
    private String gender;

}