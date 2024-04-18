package com.myproject.uniclub.service.imp;

import com.google.gson.Gson;
import com.myproject.uniclub.entity.UsersEntity;
import com.myproject.uniclub.payload.response.RoleResponse;
import com.myproject.uniclub.repositoty.UsersRepository;
import com.myproject.uniclub.service.LoginService;
import com.myproject.uniclub.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    private Gson gson = new Gson();

    @Override
    public String checkLogin(String username, String password) {
        String token = "";
        UsersEntity user = usersRepository.findByEmail(username);

        if(passwordEncoder.matches(password, user.getPassword())) {

            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setName(user.getRole_id().getName());
            roleResponse.setTest("abc");

            String role = gson.toJson(roleResponse);
            token = jwtUtils.createToken(role);
        }

        return token;
    }
}
