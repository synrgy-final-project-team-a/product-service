package com.synergy.productService.controller;


import com.synergy.productService.dto.FavoriteModel;
import com.synergy.productService.entity.Favorite;
import com.synergy.productService.repository.*;
import com.synergy.productService.service.impl.KostFavoriteServiceImpl;
import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/seeker")
@CrossOrigin("*")
@Slf4j
public class KostSeekerController {
    @Autowired
    private KostServiceImpl kostServiceImpl;

    @Autowired
    private KostFavoriteServiceImpl kostFavoriteServiceImpl;

    @Autowired
    public Response response;

    @Autowired
    private KostRepo kostRepo;

    @Autowired
    private RuleRepo ruleRepo;

    @Autowired
    private ProfileRepo profileRepo;
    
    @Autowired
    private KostRuleRepo kostRuleRepo;

    @Autowired
    private FavoriteRepo favoriteRepo;



//    @GetMapping(value = {"/kost/favorite"})
//    public ResponseEntity<Map<String, Object>> getFavoriteKost() {
//        return new ResponseEntity<>(kostFavoriteServiceImpl.getKostFavorite(), HttpStatus.OK);
//    }

//    @GetMapping(value = {"/kost/favorite/{id}"})
//    public ResponseEntity<Map<String, Object>> getFavoriteKostByProfile(@PathVariable(value = "id") Long id) {
//        return new ResponseEntity<>(kostFavoriteServiceImpl.getFavoriteKostByProfile(id), HttpStatus.OK);
//    }

    @GetMapping("/kost/favorite/{profileId}")
    public ResponseEntity<Map> getFavoriteKostByProfile(
            @PathVariable Long profileId,
            @RequestParam(required = true) Integer page,
            @RequestParam(required = true) Integer size) {
        Pageable show_data = PageRequest.of(page, size);
        Page<Favorite> list = null;
        list = favoriteRepo.findByProfileId(profileId, show_data);
        return new ResponseEntity<Map>(response.resSuccess(list, "Success get list favorite kost", 200), HttpStatus.OK);
    }
    @PostMapping("/kost/favorite/add")
    public ResponseEntity addKostToFavorite(FavoriteModel favoriteModel){
        Map<String, Object> resp = new HashMap<>();

        try{
           if ((favoriteRepo.checkExistingFavoriteKostId(favoriteModel.getKostId()) > 0)  &&
                   (favoriteRepo.checkExistingFavoriteProfileId(favoriteModel.getProfileId()) > 0)) {
                return new ResponseEntity(response.clientError("Favorite cannot be duplicated"), HttpStatus.BAD_REQUEST);
            } else if ( favoriteRepo.checkExistingAndEnabledKostId(favoriteModel.getKostId()) == 0) {
                return new ResponseEntity<>(response.notFoundError("Kost tidak berhasil ditemukan"), HttpStatus.NOT_FOUND);
            } else if (favoriteRepo.checkExistingProfileId(favoriteModel.getProfileId()) == 0) {
                return new ResponseEntity<>(response.notFoundError("Profil tidak berhasil ditemukan"), HttpStatus.NOT_FOUND);
            }
            Favorite favorite = new Favorite();
            favorite.setProfile(profileRepo.findById(favoriteModel.getProfileId()).get());
            favorite.setKost(kostRepo.findById(favoriteModel.getKostId()).get());
            favoriteRepo.save(favorite);
            resp.put("data", favorite);
            resp.put("message", "Kost telah ditambahkan ke dalam favorite");
            resp.put("status_code", 201);

            return new ResponseEntity<>(resp, HttpStatus.CREATED);

        }catch (Exception e) {
            log.error("ERROR has been found! because : {}", e.getMessage());
            return new ResponseEntity<>(response.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/kost/favorite/{favoriteId}")
    public ResponseEntity deleteKostFromFavorite(
            @PathVariable(value = "favoriteId") Long favoriteId){
        Map<String, Object> resp = new HashMap<>();

        try {
            Optional<Favorite> favorite = favoriteRepo.findById(favoriteId);
            if(favorite.isPresent()){
                favoriteRepo.deleteById(favoriteId);
                return new ResponseEntity(response.resSuccess(null, "Data berhasil dihapus", 200), HttpStatus.OK );            }
            resp.put("message", "Data tidak berhasil ditemukan");
            resp.put("status", 404);
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            resp.put("message", e.getMessage());
            resp.put("status", 500);
            log.error("ERROR has been found! because : {}", e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    /**
//     * Get Kost By Search
//     * @param city -> parameter search
//     * @param name -> parameter search
//     * @param page -> parameter indexing page (halaman ke berapa) > start dari 0,1,2,3,....
//     * @param size  -> parameter size(data yang ditampilkan) per page (1,2,3,...)
//     * @param field -> parameter sort by field (pr.price, ko.name)
//     * @param direction -> parameter sort by (asc, desc)
//     * @return data ko.kost_id, ko.name, ko.front_building_photo, pr.price,
//     *                 pr.duration_type, r.kost_type_man, r.kost_type_mixed,
//     *                 r.kost_type_woman, ko.city, ko.province
//     */
//    @GetMapping("/kost/search")
//    public ResponseEntity getKostBySearch(@RequestParam(value = "city", required = false) String city,
//                                          @RequestParam(value = "name", required = false) String name,
//                                          @RequestHeader Integer page,
//                                          @RequestHeader Integer size,
//                                          @RequestParam(value = "field", required = false) String field,
//                                          @RequestParam(value = "direction", required = false) String direction) {
//        Map<String, Object> resp = new HashMap<>();
//        try {
//            List<Object> data = kostServiceImpl.getKostBySearch(city, name, PageRequest.of(page, size, Sort.Direction.fromString(direction), field));
//            resp.put("data", data);
//            resp.put("message", "Data berhasil didapatkan");
//            resp.put("status_code", 200);
//            return new ResponseEntity(resp, HttpStatus.OK);
//        } catch (Exception e){
//            resp.put("ERROR has been found! because : {}", e.getMessage());
//            resp.put("status_code", 400);
//            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
//        }


//    }


//    /**
//     *
//     * @param ac -> parameter filter
//     * @param blanket -> parameter filter
//     * @param fan-> parameter filter
//     * @param furniture-> parameter filter
//     * @param shower-> parameter filter
//     * @param sittingCloset-> parameter filter
//     * @param springbed -> parameter filter
//     * @param table -> parameter filter
//     * @param waterHeater -> parameter filter
//     * @param insideBathroom -> parameter filter
//     * @param nonsittingCloset-> parameter filter
//     * @param outsideBathroom-> parameter filter
//     * @param kostTv -> parameter filter
//     * @param kostTypeMan -> parameter filter
//     * @param kostTypeWoman -> parameter filter
//     * @param kostTypeMixed -> parameter filter
//     * @param durationType -> parameter filter
//     * @param priceMinimum -> parameter filter
//     * @param priceMaximum -> parameter filter
//     * @param dispenser -> parameter filter
//     * @param electric -> parameter filter
//     * @param laundry -> parameter filter
//     * @param refrigerator -> parameter filter
//     * @param water -> parameter filter
//     * @param wifi -> parameter filter
//     * @param dryingGround -> parameter filter
//     * @param kitchen -> parameter filter
//     * @param livingRoom -> parameter filter
//     * @param parking -> parameter filter
//     * @param roomTv -> parameter filter
//     * @param page          -> parameter indexing page (halaman ke berapa) > start dari 0,1,2,3,....
//     * @param size          -> parameter size per page (1,2,3,...)
//     * @param field         -> parameter sort by field (pr.price, ko.name)
//     * @param direction     -> parameter sort by (asc, desc)
//     * @return data ko.kost_id, ko.name, ko.front_building_photo, pr.price,
//     *             pr.duration_type, r.kost_type_man, r.kost_type_mixed,
//     *             r.kost_type_woman, ko.city, ko.province
//     */
//    @GetMapping("/kost/filter")
//    public ResponseEntity getKostByFilter(@RequestParam(value = "ac", required = false) Boolean ac,
//                                          @RequestParam(value = "blanket", required = false) Boolean blanket,
//                                          @RequestParam(value = "fan", required = false) Boolean fan,
//                                          @RequestParam(value = "furniture", required = false) Boolean furniture,
//                                          @RequestParam(value = "shower", required = false) Boolean shower,
//                                          @RequestParam(value = "sitting_closet", required = false) Boolean sittingCloset,
//                                          @RequestParam(value = "springbed", required = false) Boolean springbed,
//                                          @RequestParam(value = "table", required = false) Boolean table,
//                                          @RequestParam(value = "water_heater", required = false) Boolean waterHeater,
//                                          @RequestParam(value = "inside_bathroom", required = false) Boolean insideBathroom,
//                                          @RequestParam(value = "non_sitting_closet", required = false) Boolean nonsittingCloset,
//                                          @RequestParam(value = "outside_bathroom", required = false) Boolean outsideBathroom,
//                                          @RequestParam(value = "kost_tv", required = false) Boolean kostTv,
//                                          @RequestParam(value = "kost_type_man", required = false) Boolean kostTypeMan,
//                                          @RequestParam(value = "kost_type_woman", required = false) Boolean kostTypeWoman,
//                                          @RequestParam(value = "kost_type_mixed", required = false) Boolean kostTypeMixed,
//                                          @RequestParam(value = "duration_type", required = false) String durationType,
//                                          @RequestParam(value = "price_minimum", required = false) Double priceMinimum,
//                                          @RequestParam(value = "price_maximum", required = false) Double priceMaximum,
//                                          @RequestParam(value = "dispenser", required = false) Boolean dispenser,
//                                          @RequestParam(value = "electric", required = false) Boolean electric,
//                                          @RequestParam(value = "laundry", required = false) Boolean laundry,
//                                          @RequestParam(value = "refrigerator", required = false) Boolean refrigerator,
//                                          @RequestParam(value = "water", required = false) Boolean water,
//                                          @RequestParam(value = "wifi", required = false) Boolean wifi,
//                                          @RequestParam(value = "drying_ground", required = false) Boolean dryingGround,
//                                          @RequestParam(value = "kitchen", required = false) Boolean kitchen,
//                                          @RequestParam(value = "living_room", required = false) Boolean livingRoom,
//                                          @RequestParam(value = "parking", required = false) Boolean parking,
//                                          @RequestParam(value = "room_tv", required = false) Boolean roomTv,
//                                          @RequestHeader Integer page,
//                                          @RequestHeader Integer size,
//                                          @RequestParam(value = "field", required = false) String field,
//                                          @RequestParam(value = "direction", required = false) String direction
//    ) {
//        Map<String, Object> resp = new HashMap<>();
//
//
//        try {
//            List<Object> data = kostServiceImpl.getKostByFilter(ac, blanket, fan, furniture, shower, sittingCloset, springbed,
//                    table, waterHeater, insideBathroom, nonsittingCloset, outsideBathroom, kostTv, kostTypeMan, kostTypeWoman,
//                    kostTypeMixed, durationType, priceMinimum, priceMaximum, dispenser, electric, laundry, refrigerator, water,
//                    wifi, dryingGround, kitchen, livingRoom, parking, roomTv, PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), field)));
//
//            resp.put("data", data);
//            resp.put("message", "Data berhasil didapatkan");
//            resp.put("status_code", 200);
//            return new ResponseEntity<>(resp, HttpStatus.OK);
//        } catch (Exception e) {
//            resp.put("message", e.getMessage());
//            resp.put("status", 400);
//            log.error("ERROR has been found! because : {}", e.getMessage());
//            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
//        }
//    }

}
