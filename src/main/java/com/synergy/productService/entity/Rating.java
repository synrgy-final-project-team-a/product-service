package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "rating")
@Where(clause = "deleted_at is null")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;

    @Column(name = "rate")
    private Long rate;

    @Lob
    @Column(name = "review")
    private String review;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kost_id", nullable = false)
    private Kost kostId;

    @Column(name = "created_at")
    private LocalTime createdAt;

    @Column(name = "updated_at")
    private LocalTime updatedAt;

    @Column(name = "deleted_at")
    private LocalTime deletedAt;

}