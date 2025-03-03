package org.example.validators;

import org.example.exception.exceptions.SubredditAlreadyExistsException;
import org.example.exception.exceptions.SubredditNotFoundException;
import org.example.exception.exceptions.UserNotFoundException;
import org.example.exception.exceptions.UsernameAlreadyExistsException;
import org.example.persistence.repository.RedditRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Validator<T1 extends RedditRepository, T2> {
    @Autowired
    protected T1 repository;

    public void validate(T2 obj) throws UserNotFoundException, UsernameAlreadyExistsException, SubredditAlreadyExistsException, SubredditNotFoundException {
        System.out.println("Validated!");
    }
}
