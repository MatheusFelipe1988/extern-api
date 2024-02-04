package com.api.extern.business.configuration;

import com.api.extern.api.dto.ProductDTO;
import com.api.extern.business.converter.ProdutoConverter;
import com.api.extern.service.entity.ProductEntity;
import com.api.extern.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final ProdutoConverter converter;

    public ProductEntity salvarProdutos(ProductEntity entity){
        try {
            return repository.save(entity);
        }catch (Exception e){
            throw new RuntimeException("error save to products" + e);
        }
    }

    public ProductDTO salvarProdutoDTO(ProductDTO productDTO){
        try {
            ProductEntity productEntity = converter.toEntity(productDTO);
            return converter.toDto(repository.save(productEntity));
        }catch (Exception e){
            throw new RuntimeException("Error save to products" + e);
        }
    }

    public List<ProductDTO> getAllProducts(){
        try {
            return converter.toListDto(repository.findAll());
        }catch (Exception e){
            throw new RuntimeException(format("error get products"), e);
        }
    }

    public ProductDTO getProductByTittle(String tittle){
        try {
            return converter.toDto(repository.findByTittle(tittle));
        }catch (Exception e){
            throw new RuntimeException(format("error get product with name", tittle) + e);
        }
    }

    public void deleteProduct(String tittle){
        try {
             repository.deleteByTittle(tittle);
        }catch (Exception e){
            throw new RuntimeException(format("error delete product", tittle) + e);
        }
    }

    public Boolean existPorTitulo(String tittle){
        try {
            return repository.existsByTittle(tittle);
        }catch (Exception e){
            throw new RuntimeException(format("error get products with tittle", tittle) + e);
        }
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO){
        try {
            ProductEntity entity = repository.findById(id).orElseThrow(() ->
                    new RuntimeException("error to update product"));
            salvarProdutos(converter.toEntityDto(entity, id, productDTO));
            return converter.toDto(repository.findByTittle(entity.getTittle()));
        }catch (Exception e){
            throw new RuntimeException(format("error update products"), e);
        }
    }
}
