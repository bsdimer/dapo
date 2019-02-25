package com.dapo.website.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dimitar Dimitrov
 */
@RestController
public class MainPageController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/",
            "/login",
            "/registration",
            "/recovery",
            "/booking/*",
            "/accept/*",
            "/meeting/*",
            "/dashboard",
            "/history",
            "/homepage",
            "/scheduling",
            "/appointment/*",
            "/account/*",
            "/dependents",
            "/role",
            "/learn-more",
            "/documents",
            "/appointments"})
    public ModelAndView mainPage() {
        return new ModelAndView("forward://index.html");
    }
}
