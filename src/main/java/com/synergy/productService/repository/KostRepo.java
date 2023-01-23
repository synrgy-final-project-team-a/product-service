package com.synergy.productService.repository;

import com.synergy.productService.entity.Kost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface KostRepo extends JpaRepository<Kost, Long> {

    @Query(value = "SELECT * FROM kost WHERE id = :kost_id", nativeQuery = true)
    public Kost getById(@PathVariable("kost_id") Long id);

}