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

    public ProductEntity toEntityDto (ProductEntity productEntity, String id, ProductDTO productDTO){
        return ProductEntity.builder()
                .id(id)
                .tittle(productDTO.getTittle() != null ? productDTO.getTittle()
                        : productEntity.getTittle())
                .category(productDTO.getCategory() != null ? productDTO.getCategory()
                        : productEntity.getCategory())
                .description(productDTO.getDescription() != null ? productDTO.getDescription()
                        : productEntity.getDescription())
                .price(productDTO.getPrice() != null ? productDTO.getPrice() : productEntity.getPrice())
                .image(productDTO.getImage() != null ? productDTO.getImage() : productEntity.getImage())
                .data_inclusao(productEntity.getData_inclusao())
                .data_update(LocalDateTime.now())
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
