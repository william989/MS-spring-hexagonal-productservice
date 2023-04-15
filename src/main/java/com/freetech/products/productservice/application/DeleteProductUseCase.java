package com.freetech.products.productservice.application;

import com.freetech.products.productservice.application.enums.ExceptionEnum;
import com.freetech.products.productservice.application.exception.BussinessException;
import com.freetech.products.productservice.application.mappers.ProductEntityMapper;
import com.freetech.products.productservice.domain.Product;
import com.freetech.products.productservice.infraestructure.adapters.out.entities.ProductEntity;
import com.freetech.products.productservice.infraestructure.ports.in.DeleteProductPort;
import com.freetech.products.productservice.infraestructure.ports.out.EntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteProductUseCase implements DeleteProductPort {
    private final EntityRepository entityRepository;

    @Override
    public Product deleteProduct(Long id) {
        var productEntity =
                entityRepository.getById(id, ProductEntity.class);
        if (productEntity == null)
            throw new BussinessException(
                    ExceptionEnum.ERROR_NOT_FOUND_PRODUCT.getCode(),
                    ExceptionEnum.ERROR_NOT_FOUND_PRODUCT.getMessage(),
                    ExceptionEnum.ERROR_NOT_FOUND_PRODUCT.getMessage(),
                    HttpStatus.NOT_FOUND);
        try {
            productEntity = entityRepository.delete(productEntity);
        }catch(Exception ex) {
        	System.out.println(ex);
        	throw new BussinessException(
                    ExceptionEnum.ERROR_DELETE_PRODUCT.getCode(),
                    ExceptionEnum.ERROR_DELETE_PRODUCT.getMessage(),
                    ExceptionEnum.ERROR_DELETE_PRODUCT.getMessage(),
                    HttpStatus.NOT_FOUND);
        }	
        return ProductEntityMapper.toDomain(productEntity);
    }
}
