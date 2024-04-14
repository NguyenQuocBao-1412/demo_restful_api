package com.myproject.uniclub.service.imp;

import com.myproject.uniclub.dto.ProductDTO;
import com.myproject.uniclub.entity.ProductEntity;
import com.myproject.uniclub.payload.request.InsertProductRequest;

import java.util.List;

public interface IProductService {
    boolean insertProduct(InsertProductRequest productRequest);

    List<ProductDTO> getAllProduct();

    ProductEntity getProductByPriceAndTagAndCategory();
}
