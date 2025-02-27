package org.example.service.impl;

import org.example.controller.model.Subreddit;
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
import org.springframework.beans.factory.annotation.Autowired;

public class UserActionsServiceImpl implements UserActionsService {
    @Autowired
    private SubredditRepository subredditRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void createSubreddit(Subreddit subreddit) throws SubredditAlreadyExistsException {

        if (subredditRepository.existsByName(subreddit.getName())) {
            throw new SubredditAlreadyExistsException("Subreddit already exists!");
        }
        subredditRepository.save(subreddit.toSubredditEntity());
    }

    @Override
    public void joinSubreddit(String sessionString, Long subbredditId) {
        SubredditEntity subredditEntity = subredditRepository.findById(subbredditId).orElse(null);
        subredditEntity.getUsers().add(userAccountRepository.findBySession_SessionString(sessionString));
        subredditRepository.save(subredditEntity);
    }

    @Override
    public void createPost(String title, String text, String sessionString, Long subredditId) throws SubredditNotFoundException {
        //check session
        if (!subredditRepository.existsById(subredditId)) {
            throw new SubredditNotFoundException("Subreddit not found!");
        }
        UserAccountEntity userAccountEntity = userAccountRepository.findBySession_SessionString(sessionString);
        SubredditEntity subredditEntity = subredditRepository.findById(subredditId).orElse(null);
        PostEntity postEntity = new PostEntity(title, text, userAccountEntity, subredditEntity);
        postRepository.save(postEntity);
    }

    @Override
    public void createComment(String sessionString, String text, Long upperCommentId, Long postId){
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
