package com.api.extern.business.configuration;

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

    public ProductEntity salvarProdutos(ProductEntity entity){
        try {
            return repository.save(entity);
        }catch (Exception e){
            throw new RuntimeException("error save to products");
        }
    }
    public List<ProductEntity> getAllProducts(){
        try {
            return repository.findAll();
        }catch (Exception e){
            throw new RuntimeException("error get all products");
        }
    }
    public Boolean existPorTitulo(String tittle){
        try {
            return repository.existsByTittle(tittle);
        }catch (Exception e){
            throw new RuntimeException(format("error get products with tittle", tittle) + e);
        }
    }
}
