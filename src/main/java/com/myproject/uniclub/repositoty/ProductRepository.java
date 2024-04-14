package com.myproject.uniclub.repositoty;

import com.myproject.uniclub.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Query("SELECT p FROM product p JOIN productdetail pd ON p.idProduct = pd.id.idProduct AND p.price > 15 AND pd.id.idTag = 1 AND pd.id.idCategory = 1")
    ProductEntity findProductsByPriceAndTagAndCategory();
}
