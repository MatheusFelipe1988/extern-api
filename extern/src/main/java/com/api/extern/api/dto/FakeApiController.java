package com.api.extern.api.dto;

import com.api.extern.business.configuration.FakeApiService;
import com.api.extern.business.configuration.ProductService;
import com.api.extern.service.entity.ProductEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/produto")
@RequiredArgsConstructor
@Tag(name = "api-fake")
public class FakeApiController {

    private final FakeApiService service;
    private final ProductService productService;

    @Operation(summary = "buscando todos os da api e salvar", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "quando a listagem dos produtos for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro na hora de listar produtos")
    })
    @PostMapping("/api")
    public ResponseEntity<List<ProductDTO>> buscaProduto(){
        return ResponseEntity.ok(service.getProducts());
    }

    @Operation(summary = "salvar todos os produtos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "na hora de realizar o cadastro for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro na hora de cadastrar")
    })
    @PostMapping("/")
    public ResponseEntity<ProductDTO> salvaProduto(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.salvarProdutoDTO(productDTO));
    }

    @Operation(summary = "Update de novos produtos", method = "PUT")
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
            @ApiResponse(responseCode = "200", description = "apagando o dado de um produto for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro na hora de apagar")
    })
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteProduto(@RequestParam ("tittle") String tittle){
        productService.deleteProduct(tittle);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Buscando produtos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "busca de todos os produtos for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro na busca de todos os produtos")
    })
    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Buscando produtos pelo nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "busca do nome caso for OK"),
            @ApiResponse(responseCode = "500", description = "quando der erro na busca")
    })
    @GetMapping("/{tittle}")
    public ResponseEntity<ProductDTO> getProductsByTittle(@PathVariable("tittle") String tittle){
        return ResponseEntity.ok(productService.getProductByTittle(tittle));
    }

    @PostMapping("/rabbit")
    public void sendMessage(@RequestBody ProductEntity request){
        log.info("Nota Fiscal: {}", request);
        service.sendMessage(request);
    }
}
