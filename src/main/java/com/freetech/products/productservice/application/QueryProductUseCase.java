package com.freetech.products.productservice.application;

import com.freetech.products.productservice.application.enums.ExceptionEnum;
import com.freetech.products.productservice.application.exception.BussinessException;
import com.freetech.products.productservice.application.mappers.ProductEntityMapper;
import com.freetech.products.productservice.domain.Product;
import com.freetech.products.productservice.infraestructure.adapters.out.entities.ProductEntity;
import com.freetech.products.productservice.infraestructure.ports.in.QueryProductPort;
import com.freetech.products.productservice.infraestructure.ports.out.EntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class QueryProductUseCase implements QueryProductPort {
    private final EntityRepository entityRepository;

    @Override
    public Product getProduct(Long id) {
        var productEntity =
                entityRepository.getById(id, ProductEntity.class);
        if (productEntity == null)
            throw new BussinessException(
                    ExceptionEnum.ERROR_NOT_FOUND_PRODUCT.getCode(),
                    ExceptionEnum.ERROR_NOT_FOUND_PRODUCT.getMessage(),
                    ExceptionEnum.ERROR_NOT_FOUND_PRODUCT.getMessage(),
                    HttpStatus.NOT_FOUND);

        return ProductEntityMapper.toDomain(productEntity);
    }
}
