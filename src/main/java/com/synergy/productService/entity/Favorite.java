package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kost_id", nullable = false)
    private Kost kost;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;

}
