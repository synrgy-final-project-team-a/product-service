package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rule")
@Where(clause = "deleted_at is null")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id", nullable = false)
    private Long id;

    @Column(name = "restricted_night")
    private Boolean restrictedNight;

    @Column(name = "identity_card")
    private Boolean identityCard;

    @Column(name = "restricted_gender")
    private Boolean restrictedGender;

    @Column(name = "restricted_guest")
    private Boolean restrictedGuest;

    @Column(name = "maxixmum_one")
    private Boolean maximumOne;

    @Column(name = "maximum_two")
    private Boolean maximumTwo;

    @Column(name = "restricted_checkout")
    private Boolean restrictedCheckout;

    @Column(name = "restricted_checkin")
    private Boolean restrictedCheckin;

    @Column(name = "include_electricity")
    private Boolean includeElectricity;

    @Column(name = "no_smoking")
    private Boolean noSmoking;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
