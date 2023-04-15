package com.freetech.products.productservice.infraestructure.ports.in;

import com.freetech.products.productservice.domain.Product;

public interface QueryProductPort {
    Product getProduct(Long id);
}
