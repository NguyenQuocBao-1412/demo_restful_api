package com.myproject.uniclub.controller;

import com.myproject.uniclub.payload.request.LoginRequest;
import com.myproject.uniclub.payload.response.BaseResponse;
import com.myproject.uniclub.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins  = "http://127.0.0.1:5500")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public ResponseEntity<?> login(@Valid LoginRequest loginRequest) {

        // Random KEY
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
//        String key = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println("kiem tra key " + key);

        BaseResponse response = new BaseResponse();
        String token = loginService.checkLogin(loginRequest.getUsername(), loginRequest.getPassword());

        response.setStatusCode(token.trim().length() > 0 ? 200 : 401);
        response.setData(token.trim().length() > 0 ? token : "Đăng nhập thất bại!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
