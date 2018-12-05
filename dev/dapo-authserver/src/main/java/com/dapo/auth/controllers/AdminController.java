package com.dapo.auth.controllers;

import com.dapo.auth.dto.AuthorityUpdateRequest;
import com.dapo.auth.dto.StandardUserDto;
import com.dapo.auth.dto.UserUpdateRequest;
import com.dapo.auth.exceptions.OperationException;
import com.dapo.auth.service.UserService;
import com.dapo.auth.service.impl.JpaUserService;
import com.dapo.common.authentication.StandardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dimer on 04.12.17.
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService<StandardUser> userService;

    @Autowired(required = false)
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public StandardUserDto saveUser(@Valid @ModelAttribute UserUpdateRequest request) throws OperationException {
        return new StandardUserDto((StandardUser) userService.update(request));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/findAllUsers", method = RequestMethod.GET)
    public List<StandardUserDto> getUsers() {
        return (List<StandardUserDto>) userService.findAll()
                .stream()
                .map(StandardUserDto::new)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/findAllUsersPaged", method = RequestMethod.GET)
    public Page<StandardUserDto> findAll(@RequestParam("scope") String scope, Pageable pageable) {
        return userService.findAll(pageable).map(StandardUserDto::new);
    }

    @RequestMapping(value = "/updateAuthorityAll", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public StandardUserDto updateAuthorityAll(@RequestBody AuthorityUpdateRequest request) throws OperationException {
        return new StandardUserDto(userService.updateAuthorityAll(request));
    }

    @RequestMapping(value = "/updateAuthority", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addAuthority(@RequestParam("username") String username,
                             @RequestParam("authority") String authority,
                             @RequestParam("scope") String scope) throws OperationException {
        userService.addUserAuthority(username, authority);
    }

    @RequestMapping(value = "/updateAuthority", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeAuthority(@RequestParam("username") String username,
                                @RequestParam("authority") String authority,
                                @RequestParam("scope") String scope) throws OperationException {
        userService.removeUserAuthority(username, authority);
    }

}
