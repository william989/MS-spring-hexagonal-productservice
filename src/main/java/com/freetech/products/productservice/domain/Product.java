package com.freetech.products.productservice.domain;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    public boolean validPrice() {
        return this.price.doubleValue() > 0.0;
    }
}