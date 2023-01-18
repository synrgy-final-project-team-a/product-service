package com.synergy.productService.repository;

import com.synergy.productService.entity.KostPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KostPhotoRepo extends JpaRepository<KostPhoto, Long> {
}
