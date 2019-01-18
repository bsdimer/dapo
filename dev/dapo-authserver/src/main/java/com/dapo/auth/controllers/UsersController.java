package com.dapo.auth.controllers;

import com.dapo.auth.exceptions.ResourceNotFoundException;
import com.dapo.auth.model.User;
import com.dapo.auth.oauth2.UserPrincipal;
import com.dapo.auth.repository.UserRepository;
import com.dapo.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by dimer on 19.04.17.
 */

@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    public User getCurrentUser(UserPrincipal userPrincipal) {
        return userService.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

}
