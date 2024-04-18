package com.myproject.uniclub.service;

import com.myproject.uniclub.dto.ProductDTO;
import com.myproject.uniclub.entity.ProductEntity;
import com.myproject.uniclub.payload.request.InsertProductRequest;

import java.util.List;

public interface ProductService {
    boolean insertProduct(InsertProductRequest productRequest);

    List<ProductDTO> getAllProduct();

    ProductEntity getProductByPriceAndTagAndCategory();
}
