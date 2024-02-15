package com.api.extern.api;

import com.api.extern.api.dto.FakeApiController;
import com.api.extern.api.dto.ProductDTO;
import com.api.extern.business.configuration.FakeApiService;
import com.api.extern.business.configuration.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FakeApiControllerTest {
    @InjectMocks
    FakeApiController controller;

    @Mock
    FakeApiService fakeApiService;

    @Mock
    ProductService productService;

    private MockMvc mockMvc;

    private String json;

    private String url;

    private ProductDTO productDTO;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setup() throws JsonProcessingException{

        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        url = "/produto";
        productDTO = ProductDTO.builder().tittle("Camisa RB").category("Camisa Oficial")
                .description("Lançamento da Red Bull Racing").price(new BigDecimal("259.99")).build();
        json = objectMapper.writeValueAsString(productDTO);
    }

    @Test
    void hustSaveProductsDTO() throws Exception{
        when(fakeApiService.getProducts()).thenReturn(Collections.singletonList(productDTO));

        mockMvc.perform(post(url + "/api")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(fakeApiService).getProducts();
        verifyNoMoreInteractions(fakeApiService);
    }

    @Test
    void notHustPostRequest() throws Exception{

        String id = "2";

        when(productService.updateProduct(id, productDTO)).thenReturn(productDTO);

        mockMvc.perform(put(url + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .param("id", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(productService).updateProduct(id, productDTO);
        verifyNoInteractions(fakeApiService);
    }

    @Test
    void hustUpdateProductsDTO() throws Exception {
        String id = "3";

        when(productService.updateProduct(id, productDTO)).thenReturn(productDTO);

        mockMvc.perform(put(url + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .param("id", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).updateProduct(id, productDTO);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void notHusteRequestisNull() throws Exception{

        mockMvc.perform(put(url + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verifyNoInteractions(productService);
    }

    @Test
    void hutsDeleteProductDTO() throws Exception{
        String tittle = "Camisa RB";

        doNothing().when(productService).deleteProduct(tittle);

        mockMvc.perform(put(url + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .param("tittle", tittle)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).deleteProduct(tittle);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void notHustPostRequestDelete() throws Exception{

        mockMvc.perform(put(url + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());

        verifyNoInteractions(productService);
    }

    @Test
    void hustGetProductDTO() throws Exception{

        String tittle = "Camisa Ferrari";

        when(productService.getProductByTittle(tittle)).thenReturn(productDTO);

        mockMvc.perform(get(url + "/" + tittle)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).getProductByTittle(tittle);
        verifyNoMoreInteractions(productService);
    }

}
