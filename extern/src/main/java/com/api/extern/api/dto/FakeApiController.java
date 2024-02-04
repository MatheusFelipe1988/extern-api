package com.api.extern.api.dto;

import com.api.extern.business.configuration.FakeApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
@Tag(name = "api-fake")
public class FakeApiController {
    private final FakeApiService service;

    @Operation(summary = "buscando todos os produtos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "quando a listagem for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro na listagem")
    })
    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> buscaProduto(){
        return ResponseEntity.ok(service.getProducts());
    }

}
