package com.myproject.uniclub.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myproject.uniclub.dto.ProductDTO;
import com.myproject.uniclub.entity.ProductDetailEntity;
import com.myproject.uniclub.entity.ProductEntity;
import com.myproject.uniclub.entity.key.ProductDetailID;
import com.myproject.uniclub.exception.InsertException;
import com.myproject.uniclub.mapper.ProductMapper;
import com.myproject.uniclub.payload.request.InsertProductRequest;
import com.myproject.uniclub.repositoty.ProductDetailRepository;
import com.myproject.uniclub.repositoty.ProductRepository;
import com.myproject.uniclub.service.imp.IFilesStorageService;
import com.myproject.uniclub.service.imp.IProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IFilesStorageService iFilesStorageService;

    // Repo
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private Gson gson = new Gson();

    @Override
    @Transactional
    public boolean insertProduct(InsertProductRequest productRequest) {
        try {
            iFilesStorageService.save(productRequest.getFile());

            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductName(productRequest.getProductName());
            productEntity.setPrice(productRequest.getPrice());
            productEntity.setImage(productRequest.getFile().getOriginalFilename());

            productRepository.save(productEntity);

            ProductDetailID id = new ProductDetailID();
            id.setIdProduct(productEntity.getIdProduct());
            id.setIdTag(productRequest.getIdTag());
            id.setIdCategory(productRequest.getIdCategory());
            id.setIdSize(productRequest.getIdSize());
            id.setIdColor(productRequest.getIdColor());

            ProductDetailEntity productDetail = new ProductDetailEntity();
            productDetail.setId(id);
            productDetail.setSoLuong(productRequest.getSoLuong());

            productDetailRepository.save(productDetail);
        } catch (Exception e) {
            throw new RuntimeException("Loi them du lieu " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        String key = "allProduct";
        redisTemplate.delete(key);
        if(redisTemplate.hasKey(key)){
            String data = redisTemplate.opsForValue().get(key).toString();
            List<ProductDTO> productDTOS = gson.fromJson(data, new TypeToken<ArrayList<ProductDTO>>() {}.getType());

            return productDTOS;
        }
        else {
            List<ProductEntity> productEntityList = productRepository.findAll();

            List<ProductDTO> productDTOS = new ArrayList<>();

            productEntityList.forEach(item -> {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setProductId(item.getIdProduct());
                productDTO.setProductName(item.getProductName());
                productDTO.setPrice(item.getPrice());
                productDTO.setImage("http://localhost:8080/file/" + item.getImage());

                productDTOS.add(productDTO);
            });
            String data = gson.toJson(productDTOS);
            redisTemplate.opsForValue().set(key,data);

            return productDTOS;
        }
    }

    @Override
    public ProductEntity getProductByPriceAndTagAndCategory() {
        return productRepository.findProductsByPriceAndTagAndCategory();
    }
}
