package com.synergy.productService.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class FilterEnabledModel {
    @NonNull
    private Boolean enabled = false;
}
