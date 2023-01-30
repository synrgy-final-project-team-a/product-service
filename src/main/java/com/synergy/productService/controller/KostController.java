package com.synergy.productService.controller;

import com.synergy.productService.entity.Kost;
import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
     * @param search      -> parameter search
     * @param page      -> parameter indexing page (halaman ke berapa) > start dari 0,1,2,3,....
     * @param size      -> parameter size(data yang ditampilkan) per page (1,2,3,...)
     * @return data entity kost
     */
    @GetMapping("/search")
    public ResponseEntity getKostBySearch(@RequestParam(value = "search", required = false) String search,
                                          @RequestParam(value = "page", required = false) @Nullable Integer page,
                                          @RequestParam(value = "size", required = false) @Nullable Integer size
                                          ) {
        Map<String, Object> resp = new HashMap<>();
        try {
            List<Map<String, Object>> data = kostService.getKostBySearch(search, PageRequest.of(page, size));
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
     * @param tableLearning              -> parameter filter
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
     * @return data entity room
     */
    @GetMapping("/filter")
    public ResponseEntity getKostByFilter(@RequestParam(value = "ac", required = false) @Nullable Boolean ac,
                                          @RequestParam(value = "blanket", required = false) @Nullable Boolean blanket,
                                          @RequestParam(value = "fan", required = false) @Nullable Boolean fan,
                                          @RequestParam(value = "furniture", required = false) @Nullable Boolean furniture,
                                          @RequestParam(value = "shower", required = false) @Nullable Boolean shower,
                                          @RequestParam(value = "sitting_closet", required = false) @Nullable Boolean sittingCloset,
                                          @RequestParam(value = "springbed", required = false) @Nullable Boolean springbed,
                                          @RequestParam(value = "table_learning", required = false) @Nullable Boolean tableLearning,
                                          @RequestParam(value = "water_heater", required = false) @Nullable Boolean waterHeater,
                                          @RequestParam(value = "inside_bathroom", required = false) @Nullable Boolean insideBathroom,
                                          @RequestParam(value = "non_sitting_closet", required = false) @Nullable Boolean nonsittingCloset,
                                          @RequestParam(value = "outside_bathroom", required = false) @Nullable Boolean outsideBathroom,
                                          @RequestParam(value = "windows", required = false) @Nullable Boolean windows,
                                          @RequestParam(value = "room_tv", required = false) @Nullable Boolean roomTv,
                                          @RequestParam(value = "kost_type_man", required = false) @Nullable Boolean kostTypeMan,
                                          @RequestParam(value = "kost_type_woman", required = false) @Nullable Boolean kostTypeWoman,
                                          @RequestParam(value = "kost_type_mixed", required = false) @Nullable Boolean kostTypeMixed,
                                          @RequestParam(value = "duration_type", required = false) @Nullable String durationType,
                                          @RequestParam(value = "price_minimum", required = false) @Nullable Double priceMinimum,
                                          @RequestParam(value = "price_maximum", required = false) @Nullable Double priceMaximum,
                                          @RequestParam(value = "kost_tv", required = false) @Nullable Boolean kostTv,
                                          @RequestParam(value = "electric", required = false) @Nullable Boolean electric,
                                          @RequestParam(value = "laundry", required = false) @Nullable Boolean laundry,
                                          @RequestParam(value = "refrigerator", required = false) @Nullable Boolean refrigerator,
                                          @RequestParam(value = "water", required = false) @Nullable Boolean water,
                                          @RequestParam(value = "wifi", required = false) @Nullable Boolean wifi,
                                          @RequestParam(value = "dispenser", required = false) @Nullable Boolean dispenser,
                                          @RequestParam(value = "drying_ground", required = false) @Nullable Boolean dryingGround,
                                          @RequestParam(value = "kitchen", required = false) @Nullable Boolean kitchen,
                                          @RequestParam(value = "living_room", required = false) @Nullable Boolean livingRoom,
                                          @RequestParam(value = "parking", required = false) @Nullable Boolean parking,
                                          @RequestParam(value = "page", required = false) @Nullable Integer page,
                                          @RequestParam(value = "size", required = false) @Nullable Integer size,
                                          @RequestParam(value = "field", required = false) @Nullable String field,
                                          @RequestParam(value = "direction", required = false) @Nullable String direction
    ) {
        Map<String, Object> resp = new HashMap<>();


        try {
            List<Map<String, Object>> data = kostService.getKostByFilter(ac, blanket, fan, furniture, shower, sittingCloset, springbed,
                    tableLearning, waterHeater, insideBathroom, nonsittingCloset, outsideBathroom, windows, roomTv,
                    kostTypeMan, kostTypeWoman, kostTypeMixed, durationType, priceMinimum, priceMaximum, kostTv, electric,
                    laundry, refrigerator, water, wifi, dispenser, dryingGround, kitchen, livingRoom, parking, PageRequest.of(page, size, Sort.Direction.fromString(direction), field));

            return new ResponseEntity<Map>(response.resSuccess(data, "Success get list kost", 200), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}