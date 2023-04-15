package com.freetech.products.productservice.application;

import com.freetech.products.productservice.application.mappers.ProductEntityMapper;
import com.freetech.products.productservice.domain.Product;
import com.freetech.products.productservice.infraestructure.adapters.out.entities.ProductEntity;
import com.freetech.products.productservice.infraestructure.ports.out.EntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateProductUseCaseTest {
    private AutoCloseable closeable;

    @Mock
    private EntityRepository entityRepository;

    @InjectMocks
    private CreateProductUseCase createProductUseCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void createProduct() {
        var product = new Product();
        product.setId(1L);
        product.setName("Parlante");
        product.setDescription("Parlante de computadora");
        product.setPrice(new BigDecimal(123.5));

        var productEntity = ProductEntityMapper.toEntity(product);

        when(entityRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        assertNotNull(createProductUseCase.createProduct(product));
    }
}