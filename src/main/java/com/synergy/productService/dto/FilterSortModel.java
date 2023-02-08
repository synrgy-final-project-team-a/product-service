package com.synergy.productService.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * POJO Class validation request parameter for filter kost
 */
@Data
public class FilterSortModel {

    @NotNull
    private Boolean ac = false;
    @NotNull
    private Boolean pillow = false;
    @NotNull
    private Boolean fan = false;
    @NotNull
    private Boolean furniture= false;
    @NotNull
    private Boolean shower= false;
    @NotNull
    private Boolean sitting_closet = false;
    @NotNull
    private Boolean springbed = false;
    @NotNull
    private Boolean table_learning = false;
    @NotNull
    private Boolean water_heater = false;
    @NotNull
    private Boolean inside_bathroom = false;
    @NotNull
    private Boolean non_sitting_closet = false;
    @NotNull
    private Boolean outside_bathroom = false;
    @NotNull
    private Boolean windows = false;
    @NotNull
    private Boolean room_tv = false;
    @Nullable
    private Boolean kost_type_man = false;
    @Nullable
    private Boolean kost_type_woman = false;
    @Nullable
    private Boolean kost_type_mixed = false;
    @Nullable
    private String duration_type = "MONTHLY";
    @Nullable
    private Double price_minimum = 0.0;
    @Nullable
    private Double price_maximum = 10000000.0;
    @NotNull
    private Boolean kost_tv = false;
    @NotNull
    private Boolean electric = false;
    @NotNull
    private Boolean laundry = false;
    @NotNull
    private Boolean refrigerator = false;
    @NotNull
    private Boolean water = false;
    @NotNull
    private Boolean wifi = false;
    @NotNull
    private Boolean dispenser = false;
    @NotNull
    private Boolean drying_ground = false;
    @NotNull
    private Boolean kitchen = false;
    @NotNull
    private Boolean living_room = false;
    @NotNull
    private Boolean parking_car = false;
    @NotNull
    private Boolean parking_motorcycle = false;
    @Nullable
    private String province = "";
    @Nullable
    private String city = "";
}
