package com.api.extern.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO{
        @JsonProperty(value = "id")
        @JsonIgnore
        private Long id;

        @JsonProperty(value = "entity_id")
        private String entityId;

        @JsonProperty(value = "tittle")
        private String tittle;
        @JsonProperty(value = "price")
        private BigDecimal price;
        @JsonProperty(value = "category")
        private String category;
        @JsonProperty(value = "description")
        private String description;
        @JsonProperty(value = "image")
        private String image;

}
