package com.synergy.productService.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

/**
 * POJO Class validation request parameter for filter kost
 */
@Data
public class FilterSortModel {

    @NonNull
    private Boolean ac = false;
    @NonNull
    private Boolean pillow = false;
    @NonNull
    private Boolean fan = false;
    @NonNull
    private Boolean furniture= false;
    @NonNull
    private Boolean shower= false;
    @NonNull
    private Boolean sitting_closet = false;
    @NonNull
    private Boolean springbed = false;
    @NonNull
    private Boolean table_learning = false;
    @NonNull
    private Boolean water_heater = false;
    @NonNull
    private Boolean inside_bathroom = false;
    @NonNull
    private Boolean non_sitting_closet = false;
    @NonNull
    private Boolean outside_bathroom = false;
    @NonNull
    private Boolean windows = false;
    @NonNull
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
    private Double price_minimum;
    @Nullable
    private Double price_maximum;
    @NonNull
    private Boolean kost_tv = false;
    @NonNull
    private Boolean electric = false;
    @NonNull
    private Boolean laundry = false;
    @NonNull
    private Boolean refrigerator = false;
    @NonNull
    private Boolean water = false;
    @NonNull
    private Boolean wifi = false;
    @NonNull
    private Boolean dispenser = false;
    @NonNull
    private Boolean drying_ground = false;
    @NonNull
    private Boolean kitchen = false;
    @NonNull
    private Boolean living_room = false;
    @NonNull
    private Boolean parking_car = false;
    @NonNull
    private Boolean parking_motorcycle = false;
    @Nullable
    private String province = "";
    @Nullable
    private String city = "";
}
