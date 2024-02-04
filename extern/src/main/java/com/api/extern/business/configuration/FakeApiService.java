package com.api.extern.business.configuration;

import com.api.extern.api.dto.ProductDTO;
import com.api.extern.business.converter.ProdutoConverter;
import com.api.extern.service.client.FakeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import static java.lang.String.format;


@Service
@RequiredArgsConstructor
public class FakeApiService {
    private final FakeApiClient fakeApiClient;
    private final ProdutoConverter produtoConverter;
    private final ProductService productService;

    public List<ProductDTO> getProducts(){
        try {
            List<ProductDTO> dto = fakeApiClient.getListProducts();
            dto.forEach(produto -> {
                Boolean retorno = productService.existPorTitulo(produto.getTittle());
                if (retorno.equals(false)){
                    productService.salvarProdutos(produtoConverter.toEntity(produto));
                }
                throw new RuntimeException(format("produto ja cadastrado no banco de dados", produto.getTittle()));
            }
            );
            return produtoConverter.toListDto(productService.getAllProducts());
        }catch (Exception e){
            throw new RuntimeException("Erro ao buscar nomes no banco de dados");
        }
    }
}
