package org.example.service.impl;

import org.example.controller.model.Subreddit;
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
    public void createSubreddit(Subreddit subreddit) {
        subredditRepository.save(subreddit.toSubredditEntity());
    }

    @Override
    public void joinSubreddit(String sessionString, String subredditName) {
        SubredditEntity subredditEntity = subredditRepository.findByName(subredditName);
        subredditEntity.getUsers().add(userAccountRepository.findBySession_SessionString(sessionString));
        subredditRepository.save(subredditEntity);
    }

    @Override
    public void createPost(String title, String text, String sessionString, String subredditName) {
        UserAccountEntity userAccountEntity = userAccountRepository.findBySession_SessionString(sessionString);
        PostEntity postEntity = new PostEntity(title, text, userAccountEntity, subredditName);
        postRepository.save(postEntity);
    }

    @Override
    public void createComment(String sessionString, String text, Long upperCommentId, Long postId){
        UserAccountEntity userAccountEntity = userAccountRepository.findBySession_SessionString(sessionString);
        PostEntity postEntity = postRepository.findPostByPostId(postId);

        CommentEntity upperComment = null;
        if(upperCommentId != null)
        {
            upperComment = commentRepository.findCommentByCommentId(upperCommentId);
        }

        CommentEntity commentEntity = new CommentEntity(userAccountEntity, text, postEntity, upperComment);
        commentRepository.save(commentEntity);
    }
}
//test commit
