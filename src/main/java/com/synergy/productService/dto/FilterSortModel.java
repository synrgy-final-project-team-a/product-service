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
    private String ac = '';
    @NotNull
    private String pillow = '';
    @NotNull
    private String fan = '';
    @NotNull
    private String furniture= '';
    @NotNull
    private String shower= '';
    @NotNull
    private String sitting_closet = '';
    @NotNull
    private String springbed = '';
    @NotNull
    private String table_learning = '';
    @NotNull
    private String water_heater = '';
    @NotNull
    private String inside_bathroom = '';
    @NotNull
    private String non_sitting_closet = '';
    @NotNull
    private String outside_bathroom = '';
    @NotNull
    private String windows = '';
    @NotNull
    private String room_tv = '';
    @Nullable
    private String kost_type_man = '';
    @Nullable
    private String kost_type_woman = '';
    @Nullable
    private String kost_type_mixed = '';
    @Nullable
    private String duration_type = 'MONTHLY';
    @Nullable
    private Double price_minimum = 0.0;
    @Nullable
    private Double price_maximum = 10000000.0;
    @NotNull
    private String kost_tv = '';
    @NotNull
    private String electric = '';
    @NotNull
    private String laundry = '';
    @NotNull
    private String refrigerator = '';
    @NotNull
    private String water = '';
    @NotNull
    private String wifi = '';
    @NotNull
    private String dispenser = '';
    @NotNull
    private String drying_ground = '';
    @NotNull
    private String kitchen = '';
    @NotNull
    private String living_room = '';
    @NotNull
    private String parking_car = '';
    @NotNull
    private String parking_motorcycle = '';
    @Nullable
    private String province = '';
    @Nullable
    private String city = '';
}
