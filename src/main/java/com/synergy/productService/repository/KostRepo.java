package com.synergy.productService.repository;

import com.synergy.productService.entity.Kost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KostRepo extends JpaRepository<Kost, Long> {
    @Query(value = "SELECT * FROM kost k WHERE k.kost_id = :id", nativeQuery = true)
    Kost checkExistingKostId(Long id);

    List<Kost> findByProfileId(Long profileId);
}