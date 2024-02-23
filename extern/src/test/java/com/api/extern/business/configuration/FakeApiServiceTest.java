package com.api.extern.business.configuration;

import com.api.extern.api.dto.ProductDTO;
import com.api.extern.business.converter.ProdutoConverter;
import com.api.extern.service.client.FakeApiClient;
import com.api.extern.service.entity.ProductEntity;
import com.api.extern.service.exception.BusinessException;
import com.api.extern.service.exception.ConflictException;
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

        ProductDTO productDTO = ProductDTO.builder().entityId("123").tittle("Camisa RB")
                .category("camisa").description("camisa season 24 da red bull racing")
                .price(new BigDecimal(259.99)).build();

        productDTOList.add(productDTO);

        ProductEntity productEntity = ProductEntity.builder().id("123").tittle("Camisa RB")
                .price(new BigDecimal(259.99)).category("camisa")
                .description("camisa season 24 da red bull racing").build();

        when(fakeApiClient.getListProducts()).thenReturn(productDTOList);
        when(productService.existPorTitulo(productDTO.getTittle())).thenReturn(false);
        when(produtoConverter.toEntity(productDTO)).thenReturn(productEntity);
        when(productService.salvarProdutos(productEntity)).thenReturn(productEntity);
        when(productService.getAllProducts()).thenReturn(productDTOList);

        List<ProductDTO> productDTOListReturn = fakeApiService.getProducts();

        assertEquals(productDTOList, productDTOListReturn);
        verify(fakeApiClient).getListProducts();
        verify(productService).existPorTitulo(productDTO.getTittle());
        verify(produtoConverter).toEntity(productDTO);
        verify(productService).salvarProdutos(productEntity);
        verify(productService).getAllProducts();
        verifyNoMoreInteractions(fakeApiClient, produtoConverter, productService);

    }

    @Test
    void hustGetProductsandNoRec(){
        List<ProductDTO> listaProductDto = new ArrayList<>();

        ProductDTO productDTO = ProductDTO.builder().entityId("123").tittle("Camisa RB")
                .category("camisa").description("camisa season 24 da red bull racing")
                .price(new BigDecimal(259.99)).build();
        listaProductDto.add(productDTO);

        when(fakeApiClient.getListProducts()).thenReturn(listaProductDto);
        when(productService.existPorTitulo(productDTO.getTittle())).thenReturn(true);

        ConflictException e = assertThrows(ConflictException.class, () -> fakeApiService.getProducts());

        assertThat(e.getMessage(), is("this product exist in databaseCamisa RB"));
        verify(fakeApiClient).getListProducts();
        verify(productService).existPorTitulo(productDTO.getTittle());
        verifyNoMoreInteractions(fakeApiClient, productService);
        verifyNoInteractions(produtoConverter);
    }

    @Test
    void hustGenerateExceptionCaseError(){
        when(fakeApiClient.getListProducts()).thenThrow(new RuntimeException("Error to get list of products"));

        BusinessException e = assertThrows(BusinessException.class, () -> fakeApiService.getProducts());

        assertThat(e.getMessage(), is("Erro ao buscar nomes no banco de dados"));
        verify(fakeApiClient).getListProducts();
        verifyNoMoreInteractions(fakeApiClient);
        verifyNoInteractions(produtoConverter, productService);
    }
}
