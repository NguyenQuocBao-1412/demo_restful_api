package com.myproject.uniclub.mapper;

import com.myproject.uniclub.dto.ProductDTO;
import com.myproject.uniclub.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity toProductEntity(ProductDTO productDTO);
}
