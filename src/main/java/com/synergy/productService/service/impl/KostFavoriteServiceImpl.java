package com.synergy.productService.service.impl;

import com.synergy.productService.entity.Favorite;
import com.synergy.productService.repository.FavoriteRepo;
import com.synergy.productService.service.KostFavorite;
import com.synergy.productService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class KostFavoriteServiceImpl implements KostFavorite {
    @Autowired
    public Response res;
    @Autowired
    private FavoriteRepo favoriteRepo;

    @Override
    public List<Favorite> getKostFavorite() {
        return favoriteRepo.findAll();
    }
}
