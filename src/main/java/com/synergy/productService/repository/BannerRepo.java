package com.synergy.productService.repository;

import com.synergy.productService.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepo extends JpaRepository<Banner, Long> {
}
