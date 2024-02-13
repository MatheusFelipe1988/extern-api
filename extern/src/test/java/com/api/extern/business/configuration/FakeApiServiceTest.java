package com.api.extern.business.configuration;

import com.api.extern.api.dto.ProductDTO;
import com.api.extern.business.converter.ProdutoConverter;
import com.api.extern.service.client.FakeApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    }
}
