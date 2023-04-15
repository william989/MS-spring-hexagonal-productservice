package com.freetech.products.productservice.infraestructure.ports.in;

import com.freetech.products.productservice.domain.Product;

public interface DeleteProductPort {
    Product deleteProduct(Long id);
}
