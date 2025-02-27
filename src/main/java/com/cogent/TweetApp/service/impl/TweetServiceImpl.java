package com.cogent.TweetApp.service.impl;

import com.cogent.TweetApp.entity.Tweet;
import com.cogent.TweetApp.entity.User;
import com.cogent.TweetApp.exception.ResourceNotFoundException;
import com.cogent.TweetApp.repository.TweetRepository;
import com.cogent.TweetApp.repository.UserRepository;
import com.cogent.TweetApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    private User retrieveByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    private Tweet retrieveByTweetId(Long tweetId) {
        return tweetRepository
                .findById(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet", "id", tweetId));
    }

    private void confirmUserExists(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new ResourceNotFoundException("User", "username", username);
        }
    }

    private void confirmTweetExists(Long tweetId) {
        if (!tweetRepository.existsById(tweetId)) {
            throw new ResourceNotFoundException("Tweet", "id", tweetId);
        }
    }

    private void checkUserHasAccess(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication != null && authentication.isAuthenticated() && authentication.getName().equals(username))) {
            throw new RuntimeException("user has no access to this resource");
        }
    }

    @Override
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public List<Tweet> getAllTweetsByUser(String username) {
        User user = retrieveByUsername(username);
        return tweetRepository.findByUserId(user.getId());
    }

    @Override
    public Tweet postTweet(String username, Tweet newTweet) {
        checkUserHasAccess(username);
        User user = retrieveByUsername(username);
        newTweet.setUser(user);
        return tweetRepository.save(newTweet);
    }

    @Override
    public Tweet updateTweet(String username, Long tweetId, Tweet updateTweet) {
        return null;
    }

    @Override
    public void deleteTweet(String username, Long tweetId) {
        checkUserHasAccess(username);
        Tweet tweet = retrieveByTweetId(tweetId);
        if(!tweet.getUser().getUsername().equals(username)) {
            throw new RuntimeException("tweet " + tweetId + " does not belong to " + username);
        }
        tweetRepository.delete(tweet);
    }

    @Override
    public Tweet likeTweet(String username, Long tweetId) {
        checkUserHasAccess(username);
        User user = retrieveByUsername(username);
        Tweet tweet = retrieveByTweetId(tweetId);
        tweet.getLikes().add(user);
        user.getLikedTweets().add(tweet);
        userRepository.save(user);
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet replyToTweet(String username, Long tweetId, Tweet newTweet) {
        checkUserHasAccess(username);
        User user = retrieveByUsername(username);
        newTweet.setUser(user);
        Tweet targetTweet = retrieveByTweetId(tweetId);
        targetTweet.getReplies().add(newTweet);
        newTweet.setParentTweet(targetTweet);
        tweetRepository.save(targetTweet);
        tweetRepository.save(newTweet);
        return newTweet;
    }
}
