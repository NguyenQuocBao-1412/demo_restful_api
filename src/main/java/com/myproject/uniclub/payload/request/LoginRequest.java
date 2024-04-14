package com.myproject.uniclub.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "User name không được phép để trống")
    private String username;

    @Size(min = 6, message = "Mật khẩu ít nhất 6 kí tự")
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

}
