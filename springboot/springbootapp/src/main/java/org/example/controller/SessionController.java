package org.example.controller;

import org.apache.coyote.Response;
import org.example.persistence.model.SessionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface SessionController {
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<SessionEntity> signup(@RequestBody SessionEntity sessionEntity);

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<SessionEntity> login(@RequestBody SessionEntity sessionEntity);

}
