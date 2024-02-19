package com.api.extern.business.configuration;

import com.api.extern.api.dto.ProductDTO;
import com.api.extern.business.converter.ProdutoConverter;
import com.api.extern.message.producer.FakeApiProducer;
import com.api.extern.service.entity.ProductEntity;
import com.api.extern.service.exception.BusinessException;
import com.api.extern.service.exception.ConflictException;
import com.api.extern.service.exception.UnprocessableEntityException;
import com.api.extern.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final ProdutoConverter converter;

    private final FakeApiProducer producer;

    public void sendMessage(ProductEntity productEntity){
        producer.sendMessage(productEntity);
    }

    public ProductEntity salvarProdutos(ProductEntity entity){
        try {
            return repository.save(entity);
        }catch (Exception e){
            throw new BusinessException("error save to products");
        }
    }

    public ProductDTO salvarProdutoDTO(ProductDTO productDTO){
        try {
            Boolean retorno = existPorTitulo(productDTO.getTittle());
            if (retorno.equals(true)){
                throw new ConflictException("this product exist in database" + productDTO.getTittle());
            }
            ProductEntity productEntity = converter.toEntity(productDTO);
            return converter.toDto(repository.save(productEntity));
        }catch (ConflictException e){
            throw new ConflictException(e.getMessage());
        }catch (Exception e){
            throw new BusinessException("Error save to products" + e);
        }
    }

    public List<ProductDTO> getAllProducts(){
        try {
            return converter.toListDto(repository.findAll());
        }catch (Exception e){
            throw new BusinessException("error get products", e);
        }
    }

    public ProductDTO getProductByTittle(String tittle){
        try {
            ProductEntity product = repository.findByTittle(tittle);
            if (Objects.isNull(product)) {
                throw new UnsupportedOperationException("nao foi encontrado o produto pelo nome" + tittle);
            }
            return converter.toDto(product);
        }catch (UnprocessableEntityException e){
            throw new UnsupportedOperationException(e.getMessage());
        }catch (Exception e){
            throw new BusinessException(format("error get product with name = %s", tittle) + e);
        }
    }

    public void deleteProduct(String tittle){
        try {
            Boolean retorno = existPorTitulo(tittle);
            if (retorno.equals(false)){
                throw new UnsupportedOperationException("impossivel deletar o produto" + tittle);
            }else {
                repository.deleteByTittle(tittle);
            }
        }catch (UnprocessableEntityException e){
            throw new UnsupportedOperationException(e.getMessage());
        }catch (Exception e){
            throw new BusinessException(format("error delete product %s", tittle) + e);
        }
    }

    public Boolean existPorTitulo(String tittle){
        try {
            return repository.existsByTittle(tittle);
        }catch (Exception e){
            throw new BusinessException(format("error get products with tittle %s", tittle) + e);
        }
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO){
        try {
            ProductEntity entity = repository.findById(id).orElseThrow(() ->
                    new UnprocessableEntityException("error product not found in the database"));
            salvarProdutos(converter.toEntityUpdate(entity, productDTO, id));
            return converter.toDto(repository.findByTittle(entity.getTittle()));
        }catch (UnprocessableEntityException e){
            throw new UnsupportedOperationException(e.getMessage());
        }catch (Exception e){
            throw new BusinessException("error update products", e);
        }
    }
}
