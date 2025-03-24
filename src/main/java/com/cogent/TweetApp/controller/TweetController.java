package com.cogent.TweetApp.controller;

import com.cogent.TweetApp.entity.Tweet;
import com.cogent.TweetApp.mapper.EntityToDTOMapper;
import com.cogent.TweetApp.payload.TweetDTO;
import com.cogent.TweetApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1.0/tweets")
@RestController
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private EntityToDTOMapper entityToDTOMapper;

    @GetMapping("/all")
    public ResponseEntity<Page<TweetDTO>> getAllTweets(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Tweet> tweets = tweetService.getAllTweets(pageable);
        Page<TweetDTO> tweetDTOS = tweets.map(tweet -> entityToDTOMapper.toTweetDTO(tweet));
        return new ResponseEntity<>(tweetDTOS, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<TweetDTO> getTweetById(@PathVariable("id") Long tweetId) {
        Tweet tweet= tweetService.getTweetById(tweetId);
        TweetDTO tweetDTO = entityToDTOMapper.toTweetDTO(tweet);
        return new ResponseEntity<>(tweetDTO, HttpStatus.OK);
    }

    @GetMapping("/all/{id}/replies")
    public ResponseEntity<Page<TweetDTO>> getAllReplyTweets(@PathVariable("id") Long tweetId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Tweet> tweets = tweetService.getAllReplyTweets(tweetId, pageable);
        Page<TweetDTO> tweetDTOS = tweets.map(tweet -> entityToDTOMapper.toTweetDTO(tweet));
        return new ResponseEntity<>(tweetDTOS, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Page<TweetDTO>> getAllTweetsByUser(@PathVariable("username") String username,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Tweet> tweets = tweetService.getAllTweetsByUser(username, pageable);
        Page<TweetDTO> tweetDTOS = tweets.map(tweet -> entityToDTOMapper.toTweetDTO(tweet));
        return new ResponseEntity<>(tweetDTOS, HttpStatus.OK);
    }

    @PostMapping("/{username}/add")
    public ResponseEntity<TweetDTO> postNewTweet(@PathVariable("username") String username,
                                                        @RequestBody Tweet newTweet) {
        Tweet tweet = tweetService.postTweet(username, newTweet);
        TweetDTO tweetDTO = entityToDTOMapper.toTweetDTO(tweet);
        return new ResponseEntity<>(tweetDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{username}/update/{id}")
    public ResponseEntity<TweetDTO> updateTweet(@PathVariable("username") String username,
                                              @PathVariable("id") Long tweetId,
                                              @RequestBody Tweet updateTweet) {
        Tweet tweet = tweetService.updateTweet(username, tweetId, updateTweet);
        TweetDTO tweetDTO = entityToDTOMapper.toTweetDTO(tweet);
        return new ResponseEntity<>(tweetDTO, HttpStatus.OK);
    }
    @PostMapping("/{username}/delete/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable("username") String username,
                                              @PathVariable("id") Long tweetId) {
        tweetService.deleteTweet(username, tweetId);
        return new ResponseEntity<>("tweet deleted successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{username}/like/{id}")
    public ResponseEntity<TweetDTO> likeTweet(@PathVariable("username") String username,
                                              @PathVariable("id") Long tweetId) {
        Tweet tweet = tweetService.likeTweet(username, tweetId);
        TweetDTO tweetDTO = entityToDTOMapper.toTweetDTO(tweet);
        return new ResponseEntity<>(tweetDTO, HttpStatus.OK);
    }

    @PostMapping("/{username}/reply/{id}")
    public ResponseEntity<TweetDTO> replyToTweet(@PathVariable("username") String username,
                                           @PathVariable("id") Long tweetId,
                                           @RequestBody Tweet newTweet) {
        Tweet tweet = tweetService.replyToTweet(username, tweetId, newTweet);
        TweetDTO tweetDTO = entityToDTOMapper.toTweetDTO(tweet);
        return new ResponseEntity<>(tweetDTO, HttpStatus.OK);
    }
}
