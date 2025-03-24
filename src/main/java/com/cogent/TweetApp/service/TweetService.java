package com.cogent.TweetApp.service;

import com.cogent.TweetApp.entity.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TweetService {
    Page<Tweet> getAllTweets(Pageable pageable);

    Page<Tweet> getAllTweetsByUser(String username, Pageable pageable);

    Page<Tweet> getAllReplyTweets(Long tweetId, Pageable pageable);

    Tweet getTweetById(Long tweetId);

    Tweet postTweet(String username, Tweet newTweet);

    Tweet updateTweet(String username, Long tweetId, Tweet updateTweet);

    void deleteTweet(String username, Long tweetId);

    Tweet likeTweet(String username, Long tweetId);

    Tweet replyToTweet(String username, Long tweetId, Tweet newTweet);
}
