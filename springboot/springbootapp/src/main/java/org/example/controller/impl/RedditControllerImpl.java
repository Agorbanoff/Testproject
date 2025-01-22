package org.example.controller.impl;

import org.example.controller.RedditController;
import org.example.service.impl.RedditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reddit")
public class RedditControllerImpl implements RedditController {
    @Autowired
    private RedditServiceImpl service;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        service.save();
        return "ok";
    }
}
