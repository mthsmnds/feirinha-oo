package com.feirinha.api.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemDTO {

    @NotBlank
    private String name;
    
    @NotNull
    @Min(1)
    private int quantity;

}
