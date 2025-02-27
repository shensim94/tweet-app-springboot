package com.cogent.TweetApp.service;

import com.cogent.TweetApp.entity.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> getAllTweets();

    List<Tweet> getAllTweetsByUser(String username);

    Tweet postTweet(String username, Tweet newTweet);

    Tweet updateTweet(String username, Long tweetId, Tweet updateTweet);

    void deleteTweet(String username, Long tweetId);

    Tweet likeTweet(String username, Long tweetId);

    Tweet replyToTweet(String username, Long tweetId, Tweet newTweet);
}
