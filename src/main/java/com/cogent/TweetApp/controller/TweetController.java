package com.cogent.TweetApp.controller;

import com.cogent.TweetApp.entity.Tweet;
import com.cogent.TweetApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1.0/tweets")
@RestController
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping("/all")
    public ResponseEntity<List<Tweet>> getAllTweets() {
        List<Tweet> tweets = tweetService.getAllTweets();
        return new ResponseEntity<>(tweets, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Tweet>> getAllTweetsByUser(@PathVariable("username") String username) {
        List<Tweet> tweets = tweetService.getAllTweetsByUser(username);
        return new ResponseEntity<>(tweets, HttpStatus.OK);
    }

    @PostMapping("/{username}/add")
    public ResponseEntity<Tweet> postNewTweet(@PathVariable("username") String username,
                                              @RequestBody Tweet newTweet) {
        Tweet tweet = tweetService.postTweet(username, newTweet);
        return new ResponseEntity<>(tweet, HttpStatus.CREATED);
    }

    @PutMapping("/{username}/update/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable("username") String username,
                                              @PathVariable("id") Long tweetId,
                                              @RequestBody Tweet updateTweet) {
        Tweet tweet = tweetService.updateTweet(username, tweetId, updateTweet);
        return new ResponseEntity<>(tweet, HttpStatus.OK);
    }
    @PostMapping("/{username}/delete/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable("username") String username,
                                              @PathVariable("id") Long tweetId) {
        tweetService.deleteTweet(username, tweetId);
        return new ResponseEntity<>("tweet deleted successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{username}/like/{id}")
    public ResponseEntity<Tweet> likeTweet(@PathVariable("username") String username,
                                              @PathVariable("id") Long tweetId) {
        Tweet tweet = tweetService.likeTweet(username, tweetId);
        return new ResponseEntity<>(tweet, HttpStatus.OK);
    }

    @PostMapping("/{username}/reply/{id}")
    public ResponseEntity<Tweet> replyToTweet(@PathVariable("username") String username,
                                           @PathVariable("id") Long tweetId,
                                           @RequestBody Tweet newTweet) {
        Tweet tweet = tweetService.replyToTweet(username, tweetId, newTweet);
        return new ResponseEntity<>(tweet, HttpStatus.OK);
    }
}
