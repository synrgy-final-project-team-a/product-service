package com.synergy.productService.repository;

import com.synergy.productService.entity.Kost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KostRepo extends JpaRepository<Kost, Long> {
}