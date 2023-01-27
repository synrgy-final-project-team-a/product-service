package com.synergy.productService.repository;

import com.synergy.productService.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite, Long> {
    List<Favorite> findByProfileId(Long profileId);


    @Query(nativeQuery = true, value = "INSERT INTO favorite (profile_id, kost_id)\n" +
            "VALUES (:profile_id, :kost_id)")
    void postKostFavorite(@Param(value = "profile_id") Long profileId,
                          @Param(value = "kost_id") Long kostId);

    @Query(nativeQuery = true, value = "DELETE FROM favorite WHERE favorite_id = :favorite_id")
    void deleteKostFavorite(@Param(value = "favorite_id") Long favoriteId);

}
