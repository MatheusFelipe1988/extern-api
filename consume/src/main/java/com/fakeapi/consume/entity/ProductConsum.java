package com.fakeapi.consume.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductConsum {

    private String id;

    private String tittle;

    private BigDecimal price;

    private String category;

    private String description;

    private String image;

    private LocalDateTime data_inclusao;

    private LocalDateTime data_update;

}
