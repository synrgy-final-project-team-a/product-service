package com.synergy.productService.controller;

import com.synergy.productService.entity.Kost;
import com.synergy.productService.repository.KostRepo;
import com.synergy.productService.repository.KostRuleRepo;
import com.synergy.productService.repository.ProfileRepo;
import com.synergy.productService.repository.RuleRepo;
import com.synergy.productService.service.impl.KostFavoriteServiceImpl;
import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
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
import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class KostAdminController {

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

    @PostMapping(value = {"/kost/approve/{id}"})
    public ResponseEntity<Map> kostApproveById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostServiceImpl.kostApprovedById(id), HttpStatus.OK);
    }

    @PostMapping(value = {"/room/approve/{id}"})
    public ResponseEntity<Map> roomApprovedById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostServiceImpl.roomApprovedById(id), HttpStatus.OK);
    }


    @DeleteMapping(value = {"/kost/reject/{id}"})
    public ResponseEntity<Map> rejectedById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostServiceImpl.kostRejectedById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/room/reject/{id}"})
    public ResponseEntity<Map> roomRejectedById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostServiceImpl.roomRejectedById(id), HttpStatus.OK);
    }

    @GetMapping("/kost/list")
    public ResponseEntity<Map> getListKostAdmin(
            @RequestParam(required = true) Integer page,
            @RequestParam(required = true) Integer size) {
        Pageable show_data = PageRequest.of(page, size);
        Page<Kost> list = null;
        list = kostRepo.getListDataAdmin(show_data);
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
        return new ResponseEntity<Map>(kostServiceImpl.getByIdTennant(id), HttpStatus.OK);
    }
}
