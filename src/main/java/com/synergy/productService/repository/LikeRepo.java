package com.synergy.productService.repository;

import com.synergy.productService.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepo extends JpaRepository<Favorite, Long> {
}
