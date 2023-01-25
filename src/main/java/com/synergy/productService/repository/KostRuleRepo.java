package com.synergy.productService.repository;

import com.synergy.productService.entity.Kost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface KostRuleRepo extends JpaRepository<Kost, Long> {
    @Transactional
    @Modifying
    @Query(value = "delete FROM kost_rule k WHERE k.kost_id = :id", nativeQuery = true)
    void deleteRuleById(Long id);
}
