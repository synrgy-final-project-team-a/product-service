package com.synergy.productService.controller;

import com.synergy.productService.dto.FilterSortModel;
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
import javax.validation.constraints.NotNull;
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
     * @param province -> parameter search
     * @param city     -> parameter search
     * @param page     -> parameter indexing page (halaman ke berapa) > start dari 0,1,2,3,....
     * @param size     -> parameter size(data yang ditampilkan) per page (1,2,3,...)
     * @return data entity kost
     */
//    }

    /**
     * Get List City and Name By Keyword Search
     *
     * @param keyword
     * @return
     */
    @GetMapping("/search-keyword")
    public ResponseEntity getAreaAndKostBySearch(@RequestParam(value = "keyword", required = false) String keyword) {

        Map<String, List<Map<String, Object>>> data = kostService.getKostBySearch(keyword);
        return new ResponseEntity<Map>(response.resSuccess(data, "Success get list kost", 200), HttpStatus.OK);
    }


    /**
     * Get Kost By Filter, Sort, And Search By Area
     *
     * @param page      -> parameter indexing page (halaman ke berapa) > start dari 0,1,2,3,....
     * @param size      -> parameter size per page (1,2,3,...)
     * @param field     -> parameter sort by field (pr.price, ko.name)
     * @param direction -> parameter sort by (asc, desc)
     * @return data entity room
     */
    @GetMapping("/filter/sort")
    public ResponseEntity getKostByFilter(@Valid FilterSortModel filterSortModel,
                                          @RequestParam(value = "page", required = true, defaultValue = "0") @NonNull Integer page,
                                          @RequestParam(value = "size", required = true, defaultValue = "6") @NotNull Integer size,
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
            if (!sortByList.contains(field)) {
                return new ResponseEntity<>(response.clientError("just provide by 'price' right now"), HttpStatus.BAD_REQUEST);
            }

            // convert sort-by to alias sql syntax parameter
            if (field.equals("price")) {
                field = "pr.price";
            }

            // throw client error if order type validation has not passed
            if (!(direction.equals("desc") || direction.equals("asc"))) {
                return new ResponseEntity<>(response.clientError("ordered by 'asc' and 'desc' only"), HttpStatus.BAD_REQUEST);
            }


            List<Map<String, Object>> data = kostService.getKostByFilterAndSortAndArea(filterSortModel, PageRequest.of(page, size, Sort.Direction.fromString(direction), field));

            return new ResponseEntity<Map>(response.resSuccess(data, "Success get list kost", 200), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response.internalServerError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET DETAIL KOST BY KOST ID
     * @param id -> kost_id
     * @return
     */
    @GetMapping(value = {"/get/{id}"})
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Long id) {
        Map<String, List<Map<String ,Object>>> data = kostService.getKostById(id);
        return new ResponseEntity<Map>(response.resSuccess(data, "Success get list kost", 200), HttpStatus.OK);
    }


    @GetMapping(value = {"/get/room/{id}"})
    public ResponseEntity getRoom(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Map>(kostService.getRoomById(id), HttpStatus.OK);
    }

    @GetMapping(value = {"/get/room/price/{roomId}"})
    public ResponseEntity<Map> getPriceByRoom(@PathVariable(value = "roomId") Long roomId) {
        return new ResponseEntity<Map>(kostService.getPricebyRoomId(roomId), HttpStatus.OK);
    }


}