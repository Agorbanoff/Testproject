package org.example.controller.impl;

import org.example.controller.SessionController;
import org.example.persistence.model.SessionEntity;
import org.example.service.impl.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reddit")
public class SessionControllerImpl implements SessionController {
    @Autowired
    private SessionServiceImpl service;

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//    public String test() {
//        service.save();
//        return "ok";
//    }

    @Override
    public ResponseEntity<SessionEntity> signup(SessionEntity sessionEntity) {
        boolean isSaved = service.saveUser(sessionEntity);
        HttpStatus status = isSaved ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(sessionEntity, status );
    }

    @Override
    public ResponseEntity<SessionEntity> login(SessionEntity sessionEntity) {

        boolean isUserExisting = service.login(sessionEntity);
        HttpStatus status = isUserExisting ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(sessionEntity, status);
    }
}
