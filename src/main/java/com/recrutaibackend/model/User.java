package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "headline")
    private String headline;

    @Column(name = "email")
    private String email;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "location_id")
    private Address location;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "is_active")
    private Boolean isActive;

    public User(
            String firstName,
            String lastName,
            String headline,
            String email,
            String hashedPassword,
            Address location
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.headline = headline;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.location = location;
        this.isActive = false;
    }
}
