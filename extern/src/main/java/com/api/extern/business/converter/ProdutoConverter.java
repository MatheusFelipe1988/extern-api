package com.api.extern.business.converter;

import com.api.extern.api.dto.ProductDTO;
import com.api.extern.service.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ProdutoConverter {

    public ProductEntity toEntity (ProductDTO productDTO){
        return ProductEntity.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .tittle(productDTO.getTittle())
                .category(productDTO.getCategory())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .image(productDTO.getImage())
                .data_inclusao(LocalDateTime.now())
                .build();
    }
    public ProductDTO toDto(ProductEntity productEntity){
        return ProductDTO.builder()
                .entityId(productEntity.getId())
                .tittle(productEntity.getTittle())
                .category(productEntity.getCategory())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .image(productEntity.getImage())
                .build();
    }
    public List<ProductDTO> toListDto(List<ProductEntity> productEntityList){
       return productEntityList.stream().map(this::toDto).toList();
    }
}
