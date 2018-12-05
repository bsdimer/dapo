package com.dapo.auth.controllers;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by dimer on 14.10.16.
 */

@Controller
public class DynamicRoutingController {

    @Autowired
    private EurekaClient discoveryClient;

    @RequestMapping("/go/{path}")
    public ModelAndView goRouter(@PathVariable("path") String path, Principal principal) {
        if (path != null && path.length() > 0) {
            String uri = discoveryClient.getNextServerFromEureka(path, false).getHomePageUrl();
            return new ModelAndView("redirect:" + uri);
        }
        return new ModelAndView("/");
    }
}
