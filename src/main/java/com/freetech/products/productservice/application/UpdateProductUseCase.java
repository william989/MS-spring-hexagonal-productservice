package com.freetech.products.productservice.application;

import com.freetech.products.productservice.application.enums.ExceptionEnum;
import com.freetech.products.productservice.application.exception.BussinessException;
import com.freetech.products.productservice.domain.Product;
import com.freetech.products.productservice.infraestructure.adapters.out.entities.ProductEntity;
import com.freetech.products.productservice.infraestructure.ports.in.UpdateProductPort;
import com.freetech.products.productservice.infraestructure.ports.out.EntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateProductUseCase implements UpdateProductPort {
    private final EntityRepository entityRepository;

    @Override
    public Product updateProduct(Product product) {
        var productEntity =
                entityRepository.getById(product.getId(), ProductEntity.class);
        if (productEntity == null)
            throw new BussinessException(
                    ExceptionEnum.ERROR_NOT_FOUND_PRODUCT.getCode(),
                    ExceptionEnum.ERROR_NOT_FOUND_PRODUCT.getMessage(),
                    ExceptionEnum.ERROR_NOT_FOUND_PRODUCT.getMessage(),
                    HttpStatus.NOT_FOUND);
        try {
            if (product.getName() != null) {
                productEntity.setName(product.getName());
            }
            if (product.getDescription() != null) {
                productEntity.setDescription(product.getDescription());
            }
            if (product.getPrice() != null) {
                productEntity.setPrice(product.getPrice());
            }
            productEntity = entityRepository.update(productEntity);
        } catch(Exception ex) {
            throw new BussinessException(
                    ExceptionEnum.ERROR_UPDATE_PRODUCT.getCode(),
                    ExceptionEnum.ERROR_UPDATE_PRODUCT.getMessage(),
                    ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return product;
    }
}
