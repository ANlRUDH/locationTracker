package com.locationtracker.repository;

import com.locationtracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u ORDER BY u.points DESC")
    List<User> findAllOrderByPointsDesc();
    
    Optional<User> findByEmail(String email);
} 