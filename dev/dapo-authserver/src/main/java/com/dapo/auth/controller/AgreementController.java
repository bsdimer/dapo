package com.dapo.auth.controller;

import com.dapo.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by dimomass on 09.02.19.
 */

@RestController
@RequestMapping("/agree")
public class AgreementController {

    private UserRepository userRepository;

    @Autowired
    public AgreementController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/collectInformation")
    public void agreedCollectInformation(Principal principal) {
        userRepository.findByEmail(principal.getName()).ifPresent(user -> {
            user.setAgreedCollectInformation(true);
            userRepository.save(user);
        });
    }

    @RequestMapping(method = RequestMethod.GET, value = "/agentContactMe")
    public void agreedAgentContactMe(Principal principal) {
        userRepository.findByEmail(principal.getName()).ifPresent(user -> {
            user.setAgreedAgentContactMe(true);
            userRepository.save(user);
        });
    }
}
