package com.synergy.productService.controller;

import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kost")
@CrossOrigin("*")
@Slf4j
public class KostController {

    @Autowired
    KostServiceImpl kostService;

    @Autowired
    Response response;

    /**
     * Get Kost By Search
     *
     * @param city      -> parameter search
     * @param name      -> parameter search
     * @param page      -> parameter indexing page (halaman ke berapa) > start dari 0,1,2,3,....
     * @param size      -> parameter size(data yang ditampilkan) per page (1,2,3,...)
     * @param field     -> parameter sort by field (pr.price, ko.name)
     * @param direction -> parameter sort by (asc, desc)
     * @return data ko.kost_id, ko.name, ko.front_building_photo, pr.price,
     * pr.duration_type, r.kost_type_man, r.kost_type_mixed,
     * r.kost_type_woman, ko.city, ko.province
     */
    @GetMapping("/search")
    public ResponseEntity getKostBySearch(@RequestParam(value = "city", required = false) String city,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "size", required = false) Integer size,
                                          @RequestParam(value = "field", required = false) String field,
                                          @RequestParam(value = "direction", required = false) String direction) {
        Map<String, Object> resp = new HashMap<>();
        try {
            List<Object> data = kostService.getKostBySearch(city, name, PageRequest.of(page, size, Sort.Direction.fromString(direction), field));
            return new ResponseEntity<Map>(response.resSuccess(data, "Success get list kost", 200), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    /**
     * @param ac                 -> parameter filter
     * @param blanket            -> parameter filter
     * @param fan->              parameter filter
     * @param furniture->        parameter filter
     * @param shower->           parameter filter
     * @param sittingCloset->    parameter filter
     * @param springbed          -> parameter filter
     * @param table              -> parameter filter
     * @param waterHeater        -> parameter filter
     * @param insideBathroom     -> parameter filter
     * @param nonsittingCloset-> parameter filter
     * @param outsideBathroom->  parameter filter
     * @param kostTv             -> parameter filter
     * @param kostTypeMan        -> parameter filter
     * @param kostTypeWoman      -> parameter filter
     * @param kostTypeMixed      -> parameter filter
     * @param durationType       -> parameter filter
     * @param priceMinimum       -> parameter filter
     * @param priceMaximum       -> parameter filter
     * @param dispenser          -> parameter filter
     * @param electric           -> parameter filter
     * @param laundry            -> parameter filter
     * @param refrigerator       -> parameter filter
     * @param water              -> parameter filter
     * @param wifi               -> parameter filter
     * @param dryingGround       -> parameter filter
     * @param kitchen            -> parameter filter
     * @param livingRoom         -> parameter filter
     * @param parking            -> parameter filter
     * @param roomTv             -> parameter filter
     * @param page               -> parameter indexing page (halaman ke berapa) > start dari 0,1,2,3,....
     * @param size               -> parameter size per page (1,2,3,...)
     * @param field              -> parameter sort by field (pr.price, ko.name)
     * @param direction          -> parameter sort by (asc, desc)
     * @return data ko.kost_id, ko.name, ko.front_building_photo, pr.price,
     * pr.duration_type, r.kost_type_man, r.kost_type_mixed,
     * r.kost_type_woman, ko.city, ko.province
     */
    @GetMapping("/filter")
    public ResponseEntity getKostByFilter(@RequestParam(value = "ac", required = false) Boolean ac,
                                          @RequestParam(value = "blanket", required = false) Boolean blanket,
                                          @RequestParam(value = "fan", required = false) Boolean fan,
                                          @RequestParam(value = "furniture", required = false) Boolean furniture,
                                          @RequestParam(value = "shower", required = false) Boolean shower,
                                          @RequestParam(value = "sitting_closet", required = false) Boolean sittingCloset,
                                          @RequestParam(value = "springbed", required = false) Boolean springbed,
                                          @RequestParam(value = "table", required = false) Boolean table,
                                          @RequestParam(value = "water_heater", required = false) Boolean waterHeater,
                                          @RequestParam(value = "inside_bathroom", required = false) Boolean insideBathroom,
                                          @RequestParam(value = "non_sitting_closet", required = false) Boolean nonsittingCloset,
                                          @RequestParam(value = "outside_bathroom", required = false) Boolean outsideBathroom,
                                          @RequestParam(value = "kost_tv", required = false) Boolean kostTv,
                                          @RequestParam(value = "kost_type_man", required = false) Boolean kostTypeMan,
                                          @RequestParam(value = "kost_type_woman", required = false) Boolean kostTypeWoman,
                                          @RequestParam(value = "kost_type_mixed", required = false) Boolean kostTypeMixed,
                                          @RequestParam(value = "duration_type", required = false) String durationType,
                                          @RequestParam(value = "price_minimum", required = false) Double priceMinimum,
                                          @RequestParam(value = "price_maximum", required = false) Double priceMaximum,
                                          @RequestParam(value = "dispenser", required = false) Boolean dispenser,
                                          @RequestParam(value = "electric", required = false) Boolean electric,
                                          @RequestParam(value = "laundry", required = false) Boolean laundry,
                                          @RequestParam(value = "refrigerator", required = false) Boolean refrigerator,
                                          @RequestParam(value = "water", required = false) Boolean water,
                                          @RequestParam(value = "wifi", required = false) Boolean wifi,
                                          @RequestParam(value = "drying_ground", required = false) Boolean dryingGround,
                                          @RequestParam(value = "kitchen", required = false) Boolean kitchen,
                                          @RequestParam(value = "living_room", required = false) Boolean livingRoom,
                                          @RequestParam(value = "parking", required = false) Boolean parking,
                                          @RequestParam(value = "room_tv", required = false) Boolean roomTv,
                                          @RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "size", required = false) Integer size,
                                          @RequestParam(value = "field", required = false) String field,
                                          @RequestParam(value = "direction", required = false) String direction
    ) {
        Map<String, Object> resp = new HashMap<>();


        try {
            List<Object> data = kostService.getKostByFilter(ac, blanket, fan, furniture, shower, sittingCloset, springbed,
                    table, waterHeater, insideBathroom, nonsittingCloset, outsideBathroom, kostTv, kostTypeMan, kostTypeWoman,
                    kostTypeMixed, durationType, priceMinimum, priceMaximum, dispenser, electric, laundry, refrigerator, water,
                    wifi, dryingGround, kitchen, livingRoom, parking, roomTv, PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), field)));

            return new ResponseEntity<Map>(response.resSuccess(data, "Success get list kost", 200), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}