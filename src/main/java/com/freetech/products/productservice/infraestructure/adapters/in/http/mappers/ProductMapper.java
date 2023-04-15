package com.freetech.products.productservice.infraestructure.adapters.in.http.mappers;

import com.freetech.products.productservice.domain.Product;
import com.freetech.products.productservice.infraestructure.adapters.in.http.dtos.NewProductDto;
import com.freetech.products.productservice.infraestructure.adapters.in.http.dtos.QueryProductDto;
import com.freetech.products.productservice.infraestructure.adapters.in.http.dtos.UpdateProductDto;
import com.freetech.products.productservice.infraestructure.adapters.in.http.dtos.DeleteProductDto;

import java.util.function.Function;
import java.util.stream.Stream;

public class ProductMapper {
    public static <T> Product toDomain(T dto) {
        if (dto == null) return null;
        return Stream.of(dto).map(toDomain()).findFirst().get();
    }

    public static <T> T toDto(Product product, Class<T> clazz) {
        if (product == null) return null;
        return (T) Stream.of(product).map(toDto(clazz)).findFirst().get();
    }

    private static <T> Function<T, Product> toDomain() {
        return dto -> {
            var product = new Product();
            if (dto instanceof NewProductDto) {
                var newProductDto = (NewProductDto) dto;
                product.setName(newProductDto.getName());
                product.setDescription(newProductDto.getDescription());
                product.setPrice(newProductDto.getPrice());
            } else if (dto instanceof UpdateProductDto) {
                var updateProductDto = (UpdateProductDto) dto;
                product.setId(updateProductDto.getId());
                product.setName(updateProductDto.getName());
                product.setDescription(updateProductDto.getDescription());
                product.setPrice(updateProductDto.getPrice());
            } else if (dto instanceof QueryProductDto) {
                var queryProductDto = (QueryProductDto) dto;
                product.setId(queryProductDto.getId());
                product.setPrice(queryProductDto.getPrice());
            } else if (dto instanceof DeleteProductDto) {
            	product = null;
            }
            return product;
        };
    }

    private static <T> Function<Product, T> toDto(Class<T> clazz) {
        return product -> {
            try {
                var dto = clazz.getDeclaredConstructor().newInstance();
                if (dto instanceof NewProductDto) {
                    ((NewProductDto) dto).setId(product.getId());
                    ((NewProductDto) dto).setName(product.getName());
                    ((NewProductDto) dto).setDescription(product.getDescription());
                    ((NewProductDto) dto).setPrice(product.getPrice());
                } else if(dto instanceof UpdateProductDto) {
                    ((UpdateProductDto) dto).setId(product.getId());
                    ((UpdateProductDto) dto).setName(product.getName());
                    ((UpdateProductDto) dto).setDescription(product.getDescription());
                    ((UpdateProductDto) dto).setPrice(product.getPrice());
                } else if(dto instanceof QueryProductDto) {
                    ((QueryProductDto) dto).setId(product.getId());
                    ((QueryProductDto) dto).setPrice(product.getPrice());
                }
                return dto;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}
