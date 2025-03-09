package org.example.service.impl;

import org.example.controller.model.CreatePostRequestDTO;
import org.example.controller.model.CreateSubredditRequestDTO;
import org.example.controller.model.JoinSubredditRequestDTO;
import org.example.controller.model.ValidationData;
import org.example.exception.exceptions.SessionExpiredException;
import org.example.persistence.model.CommentEntity;
import org.example.persistence.model.PostEntity;
import org.example.persistence.model.SubredditEntity;
import org.example.persistence.model.UserAccountEntity;
import org.example.persistence.repository.CommentRepository;
import org.example.persistence.repository.PostRepository;
import org.example.persistence.repository.SubredditRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.example.service.UserActionsService;
import org.example.validations.CreatePostValidation;
import org.example.validations.CreateSubredditValidation;
import org.example.validations.JoinSubredditValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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
    public void createSubreddit(String sessionString, CreateSubredditRequestDTO subreddit) throws Exception {
        sessionHandlingService.updateSessionIfNotExpired(sessionString);
        validationService.validate(CreateSubredditValidation.class, ValidationData.builder().createSubredditRequestDTO(subreddit).build());
        subredditRepository.save((SubredditEntity) subreddit.toEntity());
    }

    @Override
    public void joinSubreddit(String sessionString, JoinSubredditRequestDTO subreddit) throws Exception {
        sessionHandlingService.updateSessionIfNotExpired(sessionString);
        validationService.validate(JoinSubredditValidation.class, ValidationData.builder().joinSubredditRequestDTO(subreddit).build());
        SubredditEntity subredditEntity = subredditRepository.findById(subreddit.getId()).orElse(null);
        subredditEntity.getUsers().add(userAccountRepository.findBySession_SessionString(sessionString));
        subredditRepository.save(subredditEntity);
    }

    @Override
    public void createPost(CreatePostRequestDTO createPostRequestDTO, String sessionString, JoinSubredditRequestDTO subreddit) throws Exception {
        JoinSubredditRequestDTO joinSubredditRequestDTO = new JoinSubredditRequestDTO();
        joinSubredditRequestDTO.setId(subreddit.getId());
        ValidationData validationData = ValidationData.builder()
                        .joinSubredditRequestDTO(joinSubredditRequestDTO)
                        .createPostRequestDTO(createPostRequestDTO)
                        .sessionString(sessionString)
                        .build();
        sessionHandlingService.updateSessionIfNotExpired(sessionString);
        validationService.validate(CreatePostValidation.class, validationData);
        UserAccountEntity userAccountEntity = userAccountRepository.findBySession_SessionString(sessionString);
        SubredditEntity subredditEntity = subredditRepository.findById(subreddit.getId()).orElse(null);
        PostEntity postEntity = new PostEntity(createPostRequestDTO.getTitle(), createPostRequestDTO.getText(), userAccountEntity, subredditEntity);
        subredditEntity.getPosts().add(postEntity);
        subredditRepository.save(subredditEntity);
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

