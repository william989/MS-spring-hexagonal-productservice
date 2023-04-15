package com.freetech.products.productservice.infraestructure.adapters.in.http.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductDto {
    @NotNull(message = "{validation.dto.product.id.no_empty}")
    private Long id;
    @NotEmpty(message = "{validation.dto.product.name.no_empty}")
    @NotNull(message = "{validation.dto.product.name.no_empty}")
    private String name;

    @NotEmpty(message = "{validation.dto.product.description.no_empty}")
    @NotNull(message = "{validation.dto.product.description.no_empty}")
    private String description;

    @NotNull(message = "{validation.dto.product.name.no_empty}")
    private BigDecimal price;
}
