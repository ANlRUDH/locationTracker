package com.locationtracker.service;

import com.locationtracker.entity.Location;
import com.locationtracker.entity.User;
import com.locationtracker.repository.LocationRepository;
import com.locationtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Transactional
    public Location updateLocation(Long userId, String town, String district, String state, String country) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!"India".equals(country)) {
            return null; // Return null for non-Indian locations
        }

        Location location = new Location();
        location.setUser(user);
        location.setTown(town);
        location.setDistrict(district);
        location.setState(state);
        location.setCountry(country);
        location.setTimestamp(LocalDateTime.now());

        int points = calculatePoints(user, town, district, state);
        location.setPointsEarned(points);

        // Update user's current location and points
        user.setCurrentTown(town);
        user.setCurrentDistrict(district);
        user.setCurrentState(state);
        user.setCurrentCountry(country);
        user.setPoints(user.getPoints() + points);

        userRepository.save(user);
        return locationRepository.save(location);
    }

    private int calculatePoints(User user, String town, String district, String state) {
        int points = 0;

        if (!town.equals(user.getCurrentTown())) {
            points += 1;
        }
        if (!district.equals(user.getCurrentDistrict())) {
            points += 3;
        }
        if (!state.equals(user.getCurrentState())) {
            points += 10;
        }

        return points;
    }

    public List<User> getRankings() {
        return userRepository.findAllOrderByPointsDesc();
    }

    public List<Location> getUserLocationHistory(Long userId) {
        return locationRepository.findByUserIdOrderByTimestampDesc(userId);
    }
} 