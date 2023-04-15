package com.freetech.products.productservice.application.mappers;

import com.freetech.products.productservice.domain.Product;
import com.freetech.products.productservice.infraestructure.adapters.out.entities.ProductEntity;

import java.util.function.Function;
import java.util.stream.Stream;

public class ProductEntityMapper {
    public static ProductEntity toEntity(Product product) {
        if (product == null) return null;
        return Stream.of(product).map(toEntity()).findFirst().get();
    }

    public static Product toDomain(ProductEntity productEntity) {
        if (productEntity == null) return null;
        return Stream.of(productEntity).map(toDomain()).findFirst().get();
    }

    private static Function<Product, ProductEntity> toEntity() {
        return product -> {
            var productEntity = new ProductEntity();
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            return productEntity;
        };
    }

    private static Function<ProductEntity, Product> toDomain() {
        return productEntity -> {
            var product = new Product();
            product.setId(productEntity.getId());
            product.setName(productEntity.getName());
            product.setDescription(productEntity.getDescription());
            product.setPrice(productEntity.getPrice());
            return product;
        };
    }
}
