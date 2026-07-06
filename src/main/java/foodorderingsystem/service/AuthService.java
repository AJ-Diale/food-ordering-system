package foodorderingsystem.service;

import foodorderingsystem.entity.Role;
import foodorderingsystem.entity.User;
import foodorderingsystem.dto.LoginRequest;
import foodorderingsystem.response.LoginResponse;
import foodorderingsystem.dto.RegisterRequest;
import foodorderingsystem.repository.RoleRepository;
import foodorderingsystem.repository.UserRepository;
import foodorderingsystem.security.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public void registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("The email address provided is already registered to an account.");
        }

        Role customerRole = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new IllegalStateException("Default CUSTOMER role setup infrastructure missing."));

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .active(true)
                .roles(List.of(customerRole))
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public LoginResponse authenticateUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (!user.isActive()) {
            throw new IllegalArgumentException("Account inactive. Please contact system admin support.");
        }

        String token = jwtUtils.generateToken(user.getEmail());
        List<String> roles = user.getRoles().stream().map(Role::getName).toList();

        return LoginResponse.builder()
                .token(token)
                .email(user.getEmail())
                .name(user.getName())
                .roles(roles)
                .build();
    }

    /**
     * Programmatically appends the ADMIN role to an existing user account.
     */
    @Transactional
    public void assignAdminRole(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new IllegalStateException("ADMIN role setup infrastructure missing."));

        if (!user.getRoles().contains(adminRole)) {
            user.getRoles().add(adminRole);
            userRepository.save(user); // Syncs changes directly to your join table
        }
    }

    @Transactional
    public void removeAdminRole(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new IllegalStateException("ADMIN role setup infrastructure missing."));

        if (user.getRoles().contains(adminRole)) {
            user.getRoles().remove(adminRole);
            userRepository.save(user); // Syncs the removal directly to your users_roles join table
        }
    }
}