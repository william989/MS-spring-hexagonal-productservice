package com.freetech.products.productservice.infraestructure.ports.in;

import com.freetech.products.productservice.domain.Product;

public interface CreateProductPort {
    Product createProduct(Product product);
}
