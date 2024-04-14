package com.myproject.uniclub.controller;

import com.google.gson.Gson;
import com.myproject.uniclub.dto.ProductDTO;
import com.myproject.uniclub.payload.request.InsertProductRequest;
import com.myproject.uniclub.payload.response.BaseResponse;
import com.myproject.uniclub.payload.response.ResponseMessage;
import com.myproject.uniclub.service.imp.IFilesStorageService;
import com.myproject.uniclub.service.imp.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
@CrossOrigin
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IFilesStorageService iFilesStorageService;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    private Gson gson = new Gson();

    @PostMapping("/insert")
    public ResponseEntity<ResponseMessage> uploadFile(InsertProductRequest productRequest) {
        String message = "";
        iProductService.insertProduct(productRequest);

        message = "Uploaded the file successfully: " + productRequest.getFile().getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {

        BaseResponse response = new BaseResponse();
        response.setData(iProductService.getAllProduct());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        BaseResponse response = new BaseResponse();
        response.setData(iProductService.getProductByPriceAndTagAndCategory());

        logger.info(gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
