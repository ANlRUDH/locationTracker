package com.locationtracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private Integer points = 0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Location> locations;

    @Column(nullable = false)
    private String currentTown;

    @Column(nullable = false)
    private String currentDistrict;

    @Column(nullable = false)
    private String currentState;

    @Column(nullable = false)
    private String currentCountry = "India";
} 