package com.synergy.productService.repository;

import com.synergy.productService.entity.Kost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KostRepo extends JpaRepository<Kost, Long> {
    @Query(value = "SELECT k FROM Kost k WHERE k.id = :id", nativeQuery = false)
    Kost checkExistingKostId(Long id);

}