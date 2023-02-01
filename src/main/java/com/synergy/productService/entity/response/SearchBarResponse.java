package com.synergy.productService.entity.response;

import com.synergy.productService.entity.Kost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBarResponse {

    private String[] location;
    private Kost kost;
}
