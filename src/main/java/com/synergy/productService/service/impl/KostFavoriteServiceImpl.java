package com.synergy.productService.service.impl;

import com.synergy.productService.entity.Favorite;
import com.synergy.productService.repository.FavoriteRepo;
import com.synergy.productService.service.KostFavorite;
import com.synergy.productService.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KostFavoriteServiceImpl implements KostFavorite {
    @Autowired
    public Response res;
    @Autowired
    private FavoriteRepo favoriteRepo;

    private static Logger logger = LoggerFactory.getLogger(KostFavoriteServiceImpl.class);


    @Override
    public Map<String, Object> getKostFavorite() {
        try {
            List<Favorite> kostFavorite = favoriteRepo.findAll();
            return res.resSuccess(kostFavorite, "success", 200);
        }catch (Exception e) {
            return res.internalServerError(e.getMessage());
        }
    }

    @Override
    public Map<String, Object> getFavoriteKostByProfile(Long profileId) {
        try {
            List<Favorite> kostFavoriteByProfileId = favoriteRepo.findByProfileId(profileId);
            if(kostFavoriteByProfileId.size() < 1) {
                return res.notFoundError("There is no favorite kost for current user");
            }
            return res.resSuccess(kostFavoriteByProfileId, "success", 200);
        }catch (Exception e){
            return res.internalServerError(e.getMessage());
        }
    }
}
