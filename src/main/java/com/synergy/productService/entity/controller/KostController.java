package com.synergy.productService.entity.controller;

import com.synergy.productService.service.KostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tennant")
public class KostController {

    @Autowired
    public KostService kostService;

    //    @GetMapping("/kost/get/{id}")
    @GetMapping(value = {"/kost/get/{id}"})
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostService.getById(id), HttpStatus.OK);
    }
}
