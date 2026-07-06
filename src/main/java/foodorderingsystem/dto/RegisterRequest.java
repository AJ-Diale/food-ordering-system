package foodorderingsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name field cannot be left blank")
    private String name;

    @NotBlank(message = "Email field cannot be left blank")
    @Email(message = "Invalid email format string supplied")
    private String email;

    @NotBlank(message = "Password field cannot be left blank")
    @Size(min = 6, message = "Password must consist of a minimum of 6 characters")
    private String password;

    @NotBlank(message = "Phone number is a mandatory field")
    private String phoneNumber;

    private String address;
}