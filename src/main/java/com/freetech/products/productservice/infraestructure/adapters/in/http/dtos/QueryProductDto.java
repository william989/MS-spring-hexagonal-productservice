package com.freetech.products.productservice.infraestructure.adapters.in.http.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class QueryProductDto {
    private Long id;
    private BigDecimal price;
}
