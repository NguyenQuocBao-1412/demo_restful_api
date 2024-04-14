package com.myproject.uniclub.filter;

import com.google.gson.Gson;
import com.myproject.uniclub.payload.response.RoleResponse;
import com.myproject.uniclub.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomJwtFilter extends OncePerRequestFilter { //OncePerRequestFilter: class kích hoạt tất cả link

    @Autowired
    private JwtUtils jwtUtils;

    private Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuthen = request.getHeader("Authorization");

        if(headerAuthen != null && headerAuthen.trim().length() > 0) {
            String token = headerAuthen.substring(7);
            System.out.println("Ktra token "+ token);

            //Giải mã token
            String data = jwtUtils.decryptToken(token);

            if(data != null) {
                RoleResponse role = gson.fromJson(data, RoleResponse.class);
                System.out.println("kiemtra data " + data);

                List<SimpleGrantedAuthority> authorityList = new ArrayList<>(); //List<SimpleGrantedAuthority>

                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
                authorityList.add(simpleGrantedAuthority);

                //Tạo chứng thực cho Security biết là đã hợp lệ
                UsernamePasswordAuthenticationToken authen =
                        new UsernamePasswordAuthenticationToken("", "", authorityList);

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authen);
            }
        }

        filterChain.doFilter(request,response);
    }
}
