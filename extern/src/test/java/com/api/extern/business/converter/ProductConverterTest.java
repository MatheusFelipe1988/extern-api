package com.api.extern.business.converter;

import com.api.extern.api.dto.ProductDTO;
import com.api.extern.service.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductConverterTest {

    @InjectMocks
    ProdutoConverter converter;

    @Test
    void hustCoverterProductEntity(){
        ProductDTO productDTO = ProductDTO.builder().tittle("Camisa RB").category("Camisa")
                .description("Lançamento season 2024").price(new BigDecimal("329.99")).build();

        ProductEntity productEntity = ProductEntity.builder().id("1").tittle("Camisa RB").category("Camisa")
                .description("Lançamento season 2024").price(new BigDecimal("219.99")).build();

        ProductEntity product = converter.toEntity(productDTO);

        assertEquals(productEntity.getTittle(), product.getTittle());
        assertEquals(productEntity.getCategory(), product.getCategory());
        assertEquals(productEntity.getDescription(), product.getDescription());
        assertEquals(productEntity.getImage(), product.getImage());

        assertNotNull(productEntity.getId());
        assertNotNull(productEntity.getData_inclusao());
    }

}
