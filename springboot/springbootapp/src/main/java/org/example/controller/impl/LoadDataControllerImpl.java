package org.example.controller.impl;

import org.example.controller.LoadDataController;
import org.example.controller.model.CreateSubredditRequestDTO;
import org.example.service.impl.LoadDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value="/reddit")
public class LoadDataControllerImpl implements LoadDataController {

    @Autowired
    private LoadDataServiceImpl loadDataServiceimpl;

    @Override
    public ResponseEntity<List<CreateSubredditRequestDTO>> loadSubreddits(int count) {
        List<CreateSubredditRequestDTO> createSubredditRequestDTO = loadDataServiceimpl.loadSubreddits(count);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createSubredditRequestDTO);
    }
}
