package com.synergy.productService.repository;

import com.synergy.productService.entity.Kost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface KostRepo extends JpaRepository<Kost, Long> {
//    @Query(value = "SELECT k FROM Kost k WHERE k.id = :id", nativeQuery = false)
//    Kost checkExistingKostId(Long id);

    @Query(value = "SELECT * FROM kost k WHERE k.kost_id = :id AND k.enabled = true", nativeQuery = true)
    Kost checkExistingKostId(Long id);

    @Query(value = "SELECT * FROM kost k WHERE k.kost_id = :id", nativeQuery = true)
    Kost checkExistingKostIdAdmin(Long id);


    @Query(value = "select k from Kost k", nativeQuery = false)
    public Page<Kost> getListDataAdmin(Pageable pageable);

    @Query(value = "SELECT * FROM kost k WHERE k.profile_id = :profileId", nativeQuery = true)
    public Page<Kost> getListDataTennant(@Param("profileId") Long profileId, Pageable pageable);
}