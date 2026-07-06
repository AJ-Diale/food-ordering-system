package foodorderingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /*
     * Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * User's full name.
     */
    @Column(nullable = false, length = 100)
    private String name;

    /*
     * User email (must be unique).
     */
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    /*
     * Encrypted password (BCrypt).
     */
    @Column(nullable = false)
    private String password;

    /*
     * Contact number.
     */
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    /*
     * Delivery address.
     */
    @Column(length = 255)
    private String address;

    /*
     * Profile picture URL.
     */
    @Column(name = "profile_url")
    private String profileUrl;

    /*
     * Indicates whether the account is active.
     */
    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

    /*
     * Date and time the account was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /*
     * User roles (ADMIN / CUSTOMER).
     */
    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    /*
     * Automatically sets the creation timestamp.
     */
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}