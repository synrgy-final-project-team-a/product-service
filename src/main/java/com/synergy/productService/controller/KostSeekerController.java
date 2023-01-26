package com.synergy.productService.controller;


import com.synergy.productService.repository.KostRepo;
import com.synergy.productService.repository.KostRuleRepo;
import com.synergy.productService.repository.ProfileRepo;
import com.synergy.productService.repository.RuleRepo;
import com.synergy.productService.service.impl.KostFavoriteServiceImpl;
import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/seeker")
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

    @GetMapping(value = {"/kost/favorite"})
    public ResponseEntity<Map<String, Object>> getFavoriteKost() {
        return new ResponseEntity<>(kostFavoriteServiceImpl.getKostFavorite(), HttpStatus.OK);
    }

    @GetMapping(value = {"/kost/favorite/{id}"})
    public ResponseEntity<Map<String, Object>> getFavoriteKostByProfile(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(kostFavoriteServiceImpl.getFavoriteKostByProfile(id), HttpStatus.OK);
    }

}
