package com.synergy.productService.service;

import com.synergy.productService.entity.Favorite;

import java.util.List;
import java.util.Map;

public interface KostFavorite {
     Map<String, Object> getKostFavorite();
     Map<String, Object> getFavoriteKostByProfile(Long profileId);
     void deleteFavorite(Long favoriteId);
     void postFavorite(Long profileId, Long kostId);


}
