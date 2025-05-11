package com.locationtracker.controller;

import com.locationtracker.entity.Location;
import com.locationtracker.entity.User;
import com.locationtracker.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins = "*")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/update/{userId}")
    public ResponseEntity<?> updateLocation(
            @PathVariable Long userId,
            @RequestParam String town,
            @RequestParam String district,
            @RequestParam String state,
            @RequestParam String country) {
        
        Location location = locationService.updateLocation(userId, town, district, state, country);
        if (location == null) {
            return ResponseEntity.badRequest().body("Location must be in India");
        }
        return ResponseEntity.ok(location);
    }

    @GetMapping("/rankings")
    public ResponseEntity<List<User>> getRankings() {
        return ResponseEntity.ok(locationService.getRankings());
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Location>> getUserLocationHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(locationService.getUserLocationHistory(userId));
    }
} 