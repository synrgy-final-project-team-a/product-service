package com.synergy.productService.service;

import com.synergy.productService.entity.Favorite;

import java.util.List;
import java.util.Map;

public interface KostFavorite {
    public Map<String, Object> getKostFavorite();
    public Map<String, Object> getFavoriteKostByProfile(Long profileId);
}
