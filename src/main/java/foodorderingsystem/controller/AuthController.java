package foodorderingsystem.controller;

import foodorderingsystem.dto.LoginRequest;
import foodorderingsystem.dto.RegisterRequest;
import foodorderingsystem.response.ApiResponse;
import foodorderingsystem.response.LoginResponse;
import foodorderingsystem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /*
     * Register a new user.
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @Valid @RequestBody RegisterRequest request) {

        authService.registerUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        201,
                        "User registered successfully.",
                        null
                ));
    }

    /*
     * Authenticate a user and return a JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response =
                authService.authenticateUser(request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Authentication successful.",
                        response
                )
        );
    }
    @PostMapping("/assign-admin")
    public ResponseEntity<ApiResponse<Void>> assignAdmin(@org.springframework.web.bind.annotation.RequestParam String email) {
        authService.assignAdminRole(email);
        return ResponseEntity.ok(ApiResponse.success("Admin role assigned successfully to " + email, null));
    }

    @PostMapping("/remove-admin")
    public ResponseEntity<ApiResponse<Void>> removeAdmin(@org.springframework.web.bind.annotation.RequestParam String email) {
        authService.removeAdminRole(email);
        return ResponseEntity.ok(ApiResponse.success("Admin role removed successfully. User reverted to Customer.", null));
    }
}