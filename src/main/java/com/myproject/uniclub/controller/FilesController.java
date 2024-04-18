package com.myproject.uniclub.controller;

import com.google.gson.Gson;
import com.myproject.uniclub.payload.request.InsertProductRequest;
import com.myproject.uniclub.payload.response.BaseResponse;
import com.myproject.uniclub.payload.response.ResponseMessage;
import com.myproject.uniclub.service.FileService;
import com.myproject.uniclub.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FilesController {

    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;

    private Logger logger = LoggerFactory.getLogger(FilesController.class);
    private Gson gson = new Gson();

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(InsertProductRequest productRequest) {
        logger.info(gson.toJson(productRequest));

        String message = "";
        productService.insertProduct(productRequest);

        BaseResponse response = new BaseResponse();
        response.setMessage("Oke");

        logger.info(gson.toJson(response));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        Resource resource = fileService.getFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    @GetMapping("/demojava8")
    public ResponseEntity<?> demoJava8() {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");


        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
