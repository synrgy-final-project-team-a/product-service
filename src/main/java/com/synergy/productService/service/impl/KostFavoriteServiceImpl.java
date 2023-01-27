package com.synergy.productService.service.impl;

import com.synergy.productService.entity.Favorite;
import com.synergy.productService.repository.FavoriteRepo;
import com.synergy.productService.repository.KostRepo;
import com.synergy.productService.repository.ProfileRepo;
import com.synergy.productService.service.KostFavorite;
import com.synergy.productService.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class KostFavoriteServiceImpl implements KostFavorite {
    @Autowired
    public Response res;
    @Autowired
    private FavoriteRepo favoriteRepo;

    @Autowired
    KostRepo kostRepo;

    @Autowired
    ProfileRepo profileRepo;

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

    @Override
    public void postFavorite(Long profileId, Long kostId) {
        try {
            if (!kostRepo.existsById(kostId)) {
                log.error("Data kost tidak ditemukan", new NullPointerException());
            } else if (!profileRepo.existsById(profileId)) {
                log.error("Data profile tidak ditemukan", new NullPointerException());
            }
            favoriteRepo.postKostFavorite(profileId, kostId);

        } catch (Exception e) {
            log.error("Error has been found! because : {}", e.getMessage());
        }

    }

    @Override
    public void deleteFavorite(Long favoriteId) {
        try {
            if (!favoriteRepo.existsById(favoriteId)) {
                log.error("Data kost tidak ada dalam tabel favorite");
            }
            favoriteRepo.deleteKostFavorite(favoriteId);
        } catch (Exception e) {
            log.error("Error has been found! because : {}", e.getMessage());

        }
    }
}
