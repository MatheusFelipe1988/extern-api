package com.api.extern.business.configuration;

import com.api.extern.api.dto.ProductDTO;
import com.api.extern.business.converter.ProdutoConverter;
import com.api.extern.message.producer.FakeApiProducer;
import com.api.extern.service.client.FakeApiClient;
import com.api.extern.service.entity.ProductEntity;
import com.api.extern.service.error.NotificationError;
import com.api.extern.service.exception.BusinessException;
import com.api.extern.service.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FakeApiService {
    private final FakeApiClient fakeApiClient;
    private final ProdutoConverter produtoConverter;
    private final ProductService productService;

    private final FakeApiProducer producer;

    public void sendMessage(ProductEntity productEntity){
        producer.sendMessage(productEntity);
    }

    @NotificationError
    public List<ProductDTO> getProducts(){

        try {
            List<ProductDTO> dto = fakeApiClient.getListProducts();
            dto.forEach(produto -> {
                Boolean retorno = productService.existPorTitulo(produto.getTittle());
                if (retorno.equals(false)){
                    productService.salvarProdutos(produtoConverter.toEntity(produto));
                }else {
                    throw new ConflictException("this product exist in database" + produto.getTittle());
                }
            }
            );
            return productService.getAllProducts();
        } catch (ConflictException e){
            throw new ConflictException(e.getMessage());
        }catch (Exception e){
            throw new BusinessException("Erro ao buscar nomes no banco de dados");
        }

    }


}
