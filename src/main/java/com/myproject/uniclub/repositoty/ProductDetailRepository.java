package com.myproject.uniclub.repositoty;

import com.myproject.uniclub.entity.ProductDetailEntity;
import com.myproject.uniclub.entity.key.ProductDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, ProductDetailID> {
}
