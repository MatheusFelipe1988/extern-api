package com.api.extern.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "ProductEntity")
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductEntity {

    @Id
    @Column(name = "id")
    private String id;

   @Column(name = "tittle", length = 800)
    private String tittle;

   @Column(name = "price")
    private BigDecimal price;

   @Column(name = "category", length = 800)
    private String category;

   @Column(name = "description", length = 800)
    private String description;

   @Column(name = "image", length = 800)
    private String image;

   @Column(name = "data_inclusao")
    private LocalDateTime data_inclusao;

   @Column(name = "data_update")
    private LocalDateTime data_update;

}
