package com.synergy.productService.Repository;

import com.synergy.productService.Entity.Kost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KostRepo extends JpaRepository<Kost, Long> {
}