package foodorderingsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email field cannot be left blank")
    @Email(message = "Invalid email format string supplied")
    private String email;

    @NotBlank(message = "Password field cannot be left blank")
    private String password;
}