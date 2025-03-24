package com.cogent.TweetApp.repository;

import com.cogent.TweetApp.entity.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    Page<Tweet> findByUserId(Long userId, Pageable pageable);

    Page<Tweet> findByParentTweetId(Long tweetId, Pageable pageable);
}
