package com.dapo.auth.controllers;

import com.dapo.auth.dto.RegistrationRequest;
import com.dapo.auth.exceptions.OperationException;
import com.dapo.auth.service.UserService;
import com.dapo.common.authentication.StandardUser;
import com.dapo.common.dto.CheckResult;
import com.dapo.common.authentication.StandardUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.security.Principal;

/**
 * Created by dimer on 12.01.16.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerInitHandler(WebRequest request, Model model) {
        return "register";
    }

    @RequestMapping(value = "/check/username/{username:.+}", method = RequestMethod.GET)
    @ResponseBody
    public CheckResult isUserExist(@PathVariable("username") String username) {
        return CheckResult.getResult(userService.userExist(username));
    }

    @RequestMapping(value = "/check/email/{email:.+}", method = RequestMethod.GET)
    @ResponseBody
    public CheckResult isEmailExist(@PathVariable("email") String email) {
        return CheckResult.getResult(userService.emailExist(email));
    }

    @RequestMapping("/find/username/{username:.+}")
    @ResponseBody
    public StandardUser findUserByUsername(@PathVariable("username") String username) {
        return (StandardUser) userService.findByUsername(username);
    }

    @RequestMapping(value = "/register/{nonce}", method = RequestMethod.GET)
    @ResponseBody
    public StandardUser confirm(@PathVariable("nonce") String nonce) throws OperationException {
        try {
            return (StandardUser) userService.confirmRegistration(nonce);
        } catch (DuplicateKeyException e) {
            throw new OperationException("the_registration_nonce_already_confirmed", e);
        }
    }

    @RequestMapping(value = "/validate/{username:.+}", method = RequestMethod.GET)
    @ResponseBody
    public void validateUser(@PathVariable("username") String username, Principal principal) {
        userService.setValid(username);
        // tokenServices.invalidateUser("acme", username);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerHandler(@Valid @ModelAttribute RegistrationRequest request,
                                @RequestHeader("x-registration-confirm-url") String confirmUrl) throws OperationException, MalformedURLException {
        StandardUserEntity user = new StandardUserEntity(request.getUsername(), request.getPassword());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setLang(request.getLang());

        // Actual creation of the user
        userService.save(user, confirmUrl);
    }

    @RequestMapping(value = "/resendConfirmationEmail", method = RequestMethod.POST)
    @ResponseBody
    public void resendConfirmationEmail(@RequestParam("username") String username) {
        userService.resendConfirmationEmail(username);
    }

}
