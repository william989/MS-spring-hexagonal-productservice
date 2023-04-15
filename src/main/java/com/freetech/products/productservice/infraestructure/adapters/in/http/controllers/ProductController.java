package com.freetech.products.productservice.infraestructure.adapters.in.http.controllers;

import com.freetech.products.productservice.infraestructure.adapters.in.http.dtos.NewProductDto;
import com.freetech.products.productservice.infraestructure.adapters.in.http.dtos.QueryProductDto;
import com.freetech.products.productservice.infraestructure.adapters.in.http.dtos.UpdateProductDto;
import com.freetech.products.productservice.infraestructure.adapters.in.http.dtos.DeleteProductDto;
import com.freetech.products.productservice.infraestructure.adapters.in.http.mappers.ProductMapper;
import com.freetech.products.productservice.infraestructure.ports.in.CreateProductPort;
import com.freetech.products.productservice.infraestructure.ports.in.DeleteProductPort;
import com.freetech.products.productservice.infraestructure.ports.in.QueryProductPort;
import com.freetech.products.productservice.infraestructure.ports.in.UpdateProductPort;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = "v1/products")
@RestController
public class ProductController {
    private final CreateProductPort createProductPort;
    private final UpdateProductPort updateProductPort;
    private final QueryProductPort queryProductPort;
    private final DeleteProductPort deleteProductPort;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<NewProductDto> createProduct(@RequestBody @Valid NewProductDto newProductDto) {
        var product = ProductMapper.toDomain(newProductDto);
        return new ResponseEntity<>(
                ProductMapper.toDto(createProductPort.createProduct(product), NewProductDto.class),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<UpdateProductDto> updateProduct(@RequestBody @Valid UpdateProductDto updateProductDto) {
        var product = ProductMapper.toDomain(updateProductDto);
        return new ResponseEntity<>(
                ProductMapper.toDto(updateProductPort.updateProduct(product), UpdateProductDto.class),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<QueryProductDto> getProduct(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                ProductMapper.toDto(queryProductPort.getProduct(id), QueryProductDto.class),
                HttpStatus.CREATED);
    }
    /*@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)*/
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteProductDto> deleteProduct(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
        		ProductMapper.toDto(deleteProductPort.deleteProduct(id), DeleteProductDto.class),
                HttpStatus.CREATED);
    }
}
