package com.dapo.auth.controllers;

import com.dapo.auth.dto.StandardUserDto;
import com.dapo.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dimer on 19.04.17.
 */

@RestController
@RequestMapping(value = "/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{username:.+}", method = RequestMethod.GET)
    public StandardUserDto getUser(@PathVariable("username") String username, Principal principal) {
        return new StandardUserDto(userService.findByUsername(username));
    }

}
