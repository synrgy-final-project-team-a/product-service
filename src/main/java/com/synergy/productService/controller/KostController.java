package com.synergy.productService.controller;

import com.synergy.productService.entity.Kost;
import com.synergy.productService.service.impl.KostServiceImpl;
import com.synergy.productService.util.Response;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
     * Get Kost By City
     *
     * @param search      -> parameter search
     * @param page      -> parameter indexing page (halaman ke berapa) > start dari 0,1,2,3,....
     * @param size      -> parameter size(data yang ditampilkan) per page (1,2,3,...)
     * @return data entity kost
     */
    @GetMapping("/search")
    public ResponseEntity getKostBySearch(@RequestParam(value = "search", required = true, defaultValue = "Jakarta") String search,
                                          @RequestParam(value = "page", required = false, defaultValue = "0") @Nullable Integer page,
                                          @RequestParam(value = "size", required = false, defaultValue = "6") @Nullable Integer size
                                          ) {
        try {
            List<Map<String, Object>> data = kostService.getKostByArea(search, PageRequest.of(page, size));
            return new ResponseEntity<Map>(response.resSuccess(data, "Success get list kost", 200), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /** Get List City and Name By Keyword Search
     *
     * @param keyword
     * @return
     */
    @GetMapping("/search-keyword")
    public ResponseEntity getListCityAndNameBySearch(@RequestParam(value = "keyword", required = false) String keyword){

        List<Map<String,Object>> data = kostService.getKostBySearch(keyword);
        return new ResponseEntity<Map>(response.resSuccess(data, "Success get list kost", 200), HttpStatus.OK);
    }






    /** Get Kost By Filter and Sort
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
    @GetMapping("/filter/sort")
    public ResponseEntity getKostByFilter(
            @RequestParam(value = "ac", required = true, defaultValue = "false")  @NonNull Boolean ac,
                                          @RequestParam(value = "blanket", required = true, defaultValue = "false") @NonNull Boolean blanket,
                                          @RequestParam(value = "fan", required = true, defaultValue = "false") @NonNull Boolean fan,
                                          @RequestParam(value = "furniture", required = true, defaultValue = "false") @NonNull Boolean furniture,
                                          @RequestParam(value = "shower", required = true, defaultValue = "false") @NonNull Boolean shower,
                                          @RequestParam(value = "sitting_closet", required = true, defaultValue = "false") @NonNull Boolean sittingCloset,
                                          @RequestParam(value = "springbed", required = true, defaultValue = "false") @NonNull Boolean springbed,
                                          @RequestParam(value = "table_learning", required = true, defaultValue = "false") @NonNull Boolean tableLearning,
                                          @RequestParam(value = "water_heater", required = true, defaultValue = "false") @NonNull Boolean waterHeater,
                                          @RequestParam(value = "inside_bathroom", required = true, defaultValue = "false") @NonNull Boolean insideBathroom,
                                          @RequestParam(value = "non_sitting_closet", required = true, defaultValue = "false") @NonNull Boolean nonsittingCloset,
                                          @RequestParam(value = "outside_bathroom", required = true, defaultValue = "false") @NonNull Boolean outsideBathroom,
                                          @RequestParam(value = "windows", required = true, defaultValue = "false") @NonNull Boolean windows,
                                          @RequestParam(value = "room_tv", required = true, defaultValue = "false") @NonNull Boolean roomTv,
                                          @RequestParam(value = "kost_type_man", required = true, defaultValue = "false") @NonNull Boolean kostTypeMan,
                                          @RequestParam(value = "kost_type_woman", required = true, defaultValue = "false") @NonNull Boolean kostTypeWoman,
                                          @RequestParam(value = "kost_type_mixed", required = true, defaultValue = "false") @NonNull Boolean kostTypeMixed,
                                          @RequestParam(value = "duration_type", required = false) @Nullable String durationType,
                                          @RequestParam(value = "price_minimum", required = false) @Nullable Double priceMinimum,
                                          @RequestParam(value = "price_maximum", required = false) @Nullable Double priceMaximum,
                                          @RequestParam(value = "kost_tv", required = true, defaultValue = "false") @NonNull Boolean kostTv,
                                          @RequestParam(value = "electric", required = true, defaultValue = "false") @NonNull Boolean electric,
                                          @RequestParam(value = "laundry", required = true, defaultValue = "false") @NonNull Boolean laundry,
                                          @RequestParam(value = "refrigerator", required = true, defaultValue = "false") @NonNull Boolean refrigerator,
                                          @RequestParam(value = "water", required = true, defaultValue = "false") @NonNull Boolean water,
                                          @RequestParam(value = "wifi", required = true, defaultValue = "false") @NonNull Boolean wifi,
                                          @RequestParam(value = "dispenser", required = true, defaultValue = "false") @NonNull Boolean dispenser,
                                          @RequestParam(value = "drying_ground", required = true, defaultValue = "false") @NonNull Boolean dryingGround,
                                          @RequestParam(value = "kitchen", required = true, defaultValue = "false") @NonNull Boolean kitchen,
                                          @RequestParam(value = "living_room", required = true, defaultValue = "false") @NonNull Boolean livingRoom,
                                          @RequestParam(value = "parking", required = true, defaultValue = "false") @NonNull Boolean parking,
                                          @RequestParam(value = "page", required = true, defaultValue = "0" )  Integer page,
                                          @RequestParam(value = "size", required = true, defaultValue = "6")  Integer size,
                                          @RequestParam(value = "sort-by", required = false) @Nullable String field,
                                          @RequestParam(value = "order-type", required = false) @Nullable String direction
    ) {
        try {
            // field automatically convert to kost_id by default if null or empty ("") or whitespace (" ")
            field = field == null || StringUtils.isEmpty(field.trim()) ? "kost_id" : field.toLowerCase();

            // direction automatically convert to asc by default if null or empty ("") or whitespace (" ")
            direction = direction == null || StringUtils.isEmpty(direction.trim()) ? "asc" : direction.toLowerCase();

            // create list for sort-by validation
            List<String> sortByList = new ArrayList<>();
            sortByList.add("kost_id");
            sortByList.add("price");

            // throw client error if sort-by validation failed
            if(!sortByList.contains(field)){
                return new ResponseEntity<>(response.clientError("just provide by 'price' right now"),HttpStatus.BAD_REQUEST);
            }

            // convert sort-by to alias sql syntax parameter
            if(field.equals("price") ){
                field = "pr.price";
            }

            // throw client error if order type validation has not passed
            if(!(direction.equals("desc") || direction.equals("asc"))){
                return new ResponseEntity<>(response.clientError("ordered by 'asc' and 'desc' only"),HttpStatus.BAD_REQUEST);
            }

            System.out.println(ac);

            List<Map<String, Object>> data = kostService.getKostByFilter(ac, blanket, fan, furniture, shower, sittingCloset, springbed,
                    tableLearning, waterHeater, insideBathroom, nonsittingCloset, outsideBathroom, windows, roomTv,
                    kostTypeMan, kostTypeWoman, kostTypeMixed, durationType, priceMinimum, priceMaximum, kostTv, electric,
                    laundry, refrigerator, water, wifi, dispenser, dryingGround, kitchen, livingRoom, parking, PageRequest.of(page, size, Sort.Direction.fromString(direction), field));

            return new ResponseEntity<Map>(response.resSuccess(data, "Success get list kost", 200), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/get/{id}"})
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<Map>(kostService.getByIdSeeker(id), HttpStatus.OK);
    }

    @GetMapping(value = {"/get"})
    public ResponseEntity getKostById(@RequestParam(value = "id") Long id){
        List<Map<String, Object>> data = kostService.getKostById(id);
        return new ResponseEntity<Map>(response.resSuccess(data, "Success get list kost", 200), HttpStatus.OK);
    }
}