package com.api.extern.business.configuration;

import com.api.extern.api.dto.ProductDTO;
import com.api.extern.business.converter.ProdutoConverter;
import com.api.extern.service.client.FakeApiClient;
import com.api.extern.service.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FakeApiServiceTest {
    @InjectMocks
    FakeApiService fakeApiService;

    @Mock
    FakeApiClient fakeApiClient;

    @Mock
    ProductService productService;

    @Mock
    ProdutoConverter produtoConverter;

    @Test
    void hustGetProductandRecord(){
        List<ProductDTO> productDTOList = new ArrayList<>();
        ProductDTO productDTO = ProductDTO.builder().entityId("1").tittle("Camisa RB")
                .category("camisa").description("camisa season 24 da red bull racing")
                .price(new BigDecimal(259.99)).build();
        productDTOList.add(productDTO);
        ProductEntity productEntity = ProductEntity.builder().id("1").tittle("Camisa RB")
                .price(new BigDecimal(159.99)).category("camisa")
                .description("lançamento da season 2024 da formula 1").build();

        when(fakeApiClient.getListProducts()).thenReturn(productDTOList);
        when(productService.existPorTitulo(productDTO.getTittle())).thenReturn(false);
        when(produtoConverter.toEntity(productDTO)).thenReturn(productEntity);
        when();


    }
}
