package org.example.service.impl;

import org.example.controller.model.Post;
import org.example.controller.model.Subreddit;
import org.example.controller.model.ValidationData;
import org.example.exception.exceptions.SessionExpiredException;
import org.example.exception.exceptions.SubredditAlreadyExistsException;
import org.example.exception.exceptions.SubredditNotFoundException;
import org.example.persistence.model.CommentEntity;
import org.example.persistence.model.PostEntity;
import org.example.persistence.model.SubredditEntity;
import org.example.persistence.model.UserAccountEntity;
import org.example.persistence.repository.CommentRepository;
import org.example.persistence.repository.PostRepository;
import org.example.persistence.repository.SubredditRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.example.service.UserActionsService;
import org.example.service.ValidationService;
import org.example.validations.CreatePostValidation;
import org.example.validations.CreateSubredditValidation;
import org.example.validations.JoinSubredditValidation;
import org.example.validators.ExistingSubredditValidator;
import org.example.validators.ExistingUserCredentialsValidator;
import org.example.validators.UniqueSubredditValidator;
import org.example.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserActionsServiceImpl implements UserActionsService {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ValidationServiceImpl validationService;

    @Autowired
    private SessionHandlingServiceImpl sessionHandlingService;

    @Autowired
    private SubredditRepository subredditRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void createSubreddit(String sessionString, Subreddit subreddit) throws Exception {
        sessionHandlingService.updateSessionIfNotExpired(sessionString);
        validationService.validate(CreateSubredditValidation.class, ValidationData.builder().subreddit(subreddit).build());
        subredditRepository.save(subreddit.toSubredditEntity());
    }

    @Override
    public void joinSubreddit(String sessionString, Long subredditId) throws Exception {
        Subreddit subreddit = new Subreddit();
        subreddit.setId(subredditId);
        sessionHandlingService.updateSessionIfNotExpired(sessionString);
        validationService.validate(JoinSubredditValidation.class, ValidationData.builder().subreddit(subreddit).build());
        SubredditEntity subredditEntity = subredditRepository.findById(subreddit.getId()).orElse(null);
        subredditEntity.getUsers().add(userAccountRepository.findBySession_SessionString(sessionString));
        subredditRepository.save(subredditEntity);
    }

    @Override
    public void createPost(Post post, String sessionString, Long subredditId) throws Exception {
        Subreddit subreddit = new Subreddit();
        subreddit.setId(subredditId);
        ValidationData validationData = ValidationData.builder()
                        .subreddit(subreddit)
                        .post(post)
                        .sessionString(sessionString)
                        .build();
        sessionHandlingService.updateSessionIfNotExpired(sessionString);
        validationService.validate(CreatePostValidation.class, validationData);
        UserAccountEntity userAccountEntity = userAccountRepository.findBySession_SessionString(sessionString);
        SubredditEntity subredditEntity = subredditRepository.findById(subredditId).orElse(null);
        PostEntity postEntity = new PostEntity(post.getTitle(), post.getText(), userAccountEntity, subredditEntity);
        postRepository.save(postEntity);
    }

    @Override
    public void createComment(String sessionString, String text, Long upperCommentId, Long postId) throws SessionExpiredException {
        sessionHandlingService.updateSessionIfNotExpired(sessionString);
        UserAccountEntity userAccountEntity = userAccountRepository.findBySession_SessionString(sessionString);
        PostEntity postEntity = postRepository.findPostByPostId(postId);
        CommentEntity upperComment = null;
        if(upperCommentId != null) {
            upperComment = commentRepository.findCommentByCommentId(upperCommentId);
        }

        CommentEntity commentEntity = new CommentEntity(userAccountEntity, text, postEntity, upperComment);
        commentRepository.save(commentEntity);
    }


}
