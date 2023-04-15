package com.freetech.products.productservice.application;

import com.freetech.products.productservice.application.enums.ExceptionEnum;
import com.freetech.products.productservice.application.exception.BussinessException;
import com.freetech.products.productservice.application.mappers.ProductEntityMapper;
import com.freetech.products.productservice.domain.Product;
import com.freetech.products.productservice.infraestructure.ports.in.CreateProductPort;
import com.freetech.products.productservice.infraestructure.ports.out.EntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateProductUseCase implements CreateProductPort {
    private final EntityRepository entityRepository;

    @Override
    public Product createProduct(Product product) {
        try {
            var productEntity = ProductEntityMapper.toEntity(product);
            productEntity = entityRepository.save(productEntity);
            product.setId(productEntity.getId());
            return product;
        } catch(Exception ex) {
            throw new BussinessException(
                    ExceptionEnum.ERROR_CREATE_PRODUCT.getCode(),
                    ExceptionEnum.ERROR_CREATE_PRODUCT.getMessage(),
                    ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}