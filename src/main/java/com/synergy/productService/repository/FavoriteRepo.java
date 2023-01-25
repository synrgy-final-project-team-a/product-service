package com.synergy.productService.repository;

import com.synergy.productService.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite, Long> {
    public List<Favorite> findByProfileId(Long profileId);
}
