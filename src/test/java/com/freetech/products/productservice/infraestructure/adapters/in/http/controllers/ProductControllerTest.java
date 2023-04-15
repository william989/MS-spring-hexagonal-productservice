package com.freetech.products.productservice.infraestructure.adapters.in.http.controllers;

import com.freetech.products.productservice.domain.Product;
import com.freetech.products.productservice.infraestructure.adapters.in.http.dtos.NewProductDto;
import com.freetech.products.productservice.infraestructure.adapters.in.http.mappers.ProductMapper;
import com.freetech.products.productservice.infraestructure.ports.in.CreateProductPort;
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
class ProductControllerTest {

    private AutoCloseable closeable;

    @Mock
    private CreateProductPort createProductPort;

    @InjectMocks
    private ProductController productController;

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
        var newProductDto = new NewProductDto();
        newProductDto.setName("LG Pantalla 50 pulg");
        newProductDto.setDescription("Televisor de pantalla plana de 50 pulgadas");
        newProductDto.setPrice(new BigDecimal(2500.5));

        var product = ProductMapper.toDomain(newProductDto);

        when(createProductPort.createProduct(any(Product.class))).thenReturn(product);
        assertNotNull(productController.createProduct(newProductDto));
    }
}