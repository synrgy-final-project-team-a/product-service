package com.synergy.productService.repository;

import com.synergy.productService.entity.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite, Long> {
//    List<Favorite> findByProfileId(Long profileId);

    @Query(value = "SELECT * FROM favorite f WHERE f.profile_id = :profileId", nativeQuery = true)
    public Page<Favorite>  findByProfileId(Long profileId, Pageable pageable);


    @Query(nativeQuery = true, value = "INSERT INTO favorite (profile_id, kost_id)\n" +
            "VALUES (:profile_id, :kost_id)")
    void postKostFavorite(@Param(value = "profile_id") Long profileId,
                          @Param(value = "kost_id") Long kostId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM favorite f WHERE f.favorite_id = :favorite_id")
    void deleteKostFavorite(@Param(value = "favorite_id") Long favoriteId);

    @Query(nativeQuery = true, value = "SELECT count(kost_id) FROM kost k WHERE k.kost_id = :kost_id AND k.enabled = true AND k.deleted_at is null")
    Integer checkExistingAndEnabledKostId(@Param("kost_id") Long id);

    @Query(nativeQuery = true, value = "SELECT count(id) FROM profile p WHERE p.id = :profile_id AND p.deleted_at is null")
    Integer checkExistingProfileId(@Param("profile_id") Long id);

    @Query(nativeQuery = true, value = "select count(kost_id) from favorite f where f.kost_id = :kost_id ")
    Integer checkExistingFavoriteKostId(@Param("kost_id") Long id);

    @Query(nativeQuery = true, value = "select count(profile_id) from favorite f where f.profile_id = :profile_id ")
    Integer checkExistingFavoriteProfileId(@Param("profile_id") Long id);

}
