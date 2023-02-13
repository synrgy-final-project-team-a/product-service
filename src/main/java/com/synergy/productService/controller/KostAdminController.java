package com.synergy.productService.controller;

import com.synergy.productService.dto.BannerModel;
import com.synergy.productService.dto.KostModel;
import com.synergy.productService.entity.*;
import com.synergy.productService.repository.*;
import com.synergy.productService.service.impl.KostFavoriteServiceImpl;
import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.*;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@Slf4j
public class KostAdminController {

    @Autowired
    public Response res;
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
    private BannerRepo bannerRepo;

    @PostMapping(value = {"/kost/approve/{id}"})
    public ResponseEntity<Map> kostApproveById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostServiceImpl.kostApprovedById(id), HttpStatus.OK);
    }

//    @PostMapping(value = {"/room/approve/{id}"})
//    public ResponseEntity<Map> roomApprovedById(@PathVariable(value = "id") Long id){
//        return new ResponseEntity<Map>(kostServiceImpl.roomApprovedById(id), HttpStatus.OK);
//    }


    @DeleteMapping(value = {"/kost/reject/{id}"})
    public ResponseEntity<Map> rejectedById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostServiceImpl.kostRejectedById(id), HttpStatus.OK);
    }

//    @DeleteMapping(value = {"/room/reject/{id}"})
//    public ResponseEntity<Map> roomRejectedById(@PathVariable(value = "id") Long id){
//        return new ResponseEntity<Map>(kostServiceImpl.roomRejectedById(id), HttpStatus.OK);
//    }

    @GetMapping("/kost/list")
    public ResponseEntity<Map> getListKostAdmin(
            @RequestParam(required = true) Integer page,
            @RequestParam(required = true) Integer size,
            @RequestParam(required = false) Boolean enabled) {
        Pageable show_data = PageRequest.of(page, size);
        Page<Kost> list = null;
        list = kostRepo.getListDataAdmin(enabled, show_data);
        return new ResponseEntity<Map>(response.resSuccess(list, "Success get list kost", 400), HttpStatus.OK);
    }

    @DeleteMapping("/kost/delete/{id}")
    public ResponseEntity<Map> deleteKostById(@PathVariable Long id) {
        Kost kost = kostRepo.checkExistingKostIdAdmin(id);

        // implement soft delete by set DeletedAt
        kost.setDeletedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

        kostRepo.save(kost);
        Kost obj = kostRepo.checkExistingKostId(id);

        return new ResponseEntity<Map>(response.resSuccess(obj, "Success delete kost!", 200), HttpStatus.OK);
    }

    @GetMapping(value = {"/kost/get/{id}"})
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostServiceImpl.getKostByIdTennantAdmin(id), HttpStatus.OK);
    }

    @PostMapping(value = {"/banner"})
    public ResponseEntity<Map> postBanner(@ModelAttribute BannerModel bannerModel) {
        Map<String, Object> resp = new HashMap<>();
        try {
            Banner banner = new Banner();
            banner.setBannerName(bannerModel.getBannerName());
            banner.setBannerImage(kostServiceImpl.uploadFile(bannerModel.getBannerImage(), "banner_image"));
            Banner saveBanner = bannerRepo.save(banner);

            return new ResponseEntity<>(res.resSuccess(saveBanner, "Success edit kost!", 200), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(res.clientError(e.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping(value = {"/banner/{id}"})
    public ResponseEntity<Map> getBannerById(@PathVariable(value = "id") Long id){
        Map<String, Object> resp = new HashMap<>();
        try {
            Optional<Banner> banner = bannerRepo.findById(id);
            if(banner.isPresent()){
                Banner getBanner = bannerRepo.getById(id);
                return new ResponseEntity(response.resSuccess(getBanner, "Success get Banner", 200), HttpStatus.OK );            }
            resp.put("message", "Data cannot be found");
            resp.put("status", 404);
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            resp.put("message", e.getMessage());
            resp.put("status", 500);
            log.error("ERROR has been found! because : {}", e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/banner/list"})
    public ResponseEntity<Map> getListBanner() {
        try {
            List<Banner> list = null;
            list = bannerRepo.findAll();
            return new ResponseEntity<Map>(response.resSuccess(list, "Success get list banner", 200), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(res.clientError(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/banner/{id}")
    public ResponseEntity<Map<String, Object>> deleteBannerById(@PathVariable Long id) {
        try{
            Optional<Banner> banner = bannerRepo.findById(id);

            if (banner.isPresent()) {
                banner.get().setDeletedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
                Banner bannerDeleted = bannerRepo.save(banner.get());
                return new ResponseEntity<>(res.resSuccess(bannerDeleted, "success", 200), HttpStatus.OK);
            }
            return new ResponseEntity<>(res.notFoundError("Banner doesn't exist"), HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(res.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
