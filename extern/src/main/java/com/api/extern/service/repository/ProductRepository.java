package com.api.extern.service.repository;

import com.api.extern.service.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    Boolean existsByTittle(String tittle);

    ProductEntity findByTittle(String tittle);

    @Transactional
    void deleteByTittle(String tittle);

}
