package com.myproject.uniclub.security;

import com.myproject.uniclub.filter.CustomJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomJwtFilter customJwtFilter;

    // Khởi tạo chuẩn mã hóa và lưu trên IOC
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Tạo danh sách user lưu tạm trên RAM
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails admin = User.withUsername("admin")
//                .password(
//                    passwordEncoder().encode("123")
//                )
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("user")
//                .password(
//                        passwordEncoder().encode("123456")
//                )
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    /**
     * Thay đổi thông tin cấu hình của Security : Phân quyền, chứng thực, ...
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(d -> d.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( a -> {
                        //Login
                        a.requestMatchers("/login/**", "/test/**").permitAll();
                        a.requestMatchers("/file/**").permitAll();
                        a.requestMatchers(HttpMethod.GET,"/product/**").hasRole("ADMIN");

                        //Mails
                    a.requestMatchers(("/mails/**")).permitAll();

                        //anyRequest
                        a.anyRequest().authenticated();
                    }
                )
                .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/role").permitAll()
//                .requestMatchers("/role/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and().build();
    }
}
