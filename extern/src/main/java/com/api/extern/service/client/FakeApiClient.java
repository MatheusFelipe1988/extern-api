package com.api.extern.service.client;

import com.api.extern.api.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "extern-api", url = "${api-extern.url}:#{null}")
public interface FakeApiClient {
    @GetMapping("/products")
    List<ProductDTO> getListProducts();
}
