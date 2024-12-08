package org.example.controller.impl;

import org.example.controller.RedditController;
import org.example.service.impl.RedditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RedditControllerImpl implements RedditController {
    @Autowired
    private RedditServiceImpl service;
}
