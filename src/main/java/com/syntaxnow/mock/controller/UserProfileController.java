package com.syntaxnow.mock.controller;

import com.syntaxnow.mock.model.Profile;
import com.syntaxnow.mock.repository.ProRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private final ProRepo repository;


    @GetMapping
    public List<Profile> getAll() {
        return repository.findAll();
    }


//    @PostMapping
//    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
//
//        if (profile.getEmployee() != null) {
//            profile.getEmployee().setProfile(profile);
//        }
//
//        Profile savedProfile = repository.save(profile);
//        return ResponseEntity.ok(savedProfile);
//    }

}
