package com.freetech.products.productservice.infraestructure.adapters.out.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Setter
@Entity
@Table(schema="public", name="products")
public class ProductEntity {
    @Id
    @SequenceGenerator(name = "PRD_GENERATOR", sequenceName = "public.seq_products", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRD_GENERATOR")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
