package com.api.extern.api.dto;

import com.api.extern.business.configuration.FakeApiService;
import com.api.extern.business.configuration.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
@Tag(name = "api-fake")
public class FakeApiController {

    private final FakeApiService service;
    private final ProductService productService;

    @Operation(summary = "buscando todos os da api e salvar", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "quando a listagem for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro na listagem")
    })
    @PostMapping("/api")
    public ResponseEntity<List<ProductDTO>> buscaProduto(){
        return ResponseEntity.ok(service.getProducts());
    }

    @Operation(summary = "salvar todos os produtos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "quando o cadastro for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro no cadastro")
    })
    @PostMapping("/")
    public ResponseEntity<ProductDTO> salvaProduto(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.salvarProdutoDTO(productDTO));
    }

    @Operation(summary = "Update de novos produtos", method = "UPDATE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "atualizando produtos for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro no update")
    })
    @PutMapping("/")
    public ResponseEntity<ProductDTO> updateProduto(@RequestParam ("id") String id, @RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @Operation(summary = "Deletando produtos", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete de produtos for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro no delete")
    })
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteProduto(@RequestParam ("tittle") String tittle){
        productService.deleteProduct(tittle);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Buscando produtos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "busca de produtos for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro na busca")
    })
    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Buscando produtos pelo nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "busca do nome for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro na busca")
    })
    @GetMapping("/{tittle}")
    public ResponseEntity<ProductDTO> getProductsByTittle(@PathVariable("tittle") String tittle){
        return ResponseEntity.ok(productService.getProductByTittle(tittle));
    }
}
