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
    Page<Tweet> findByUserIdAndIdLessThan(Long userId, Long lastId, Pageable pageable);

    Page<Tweet> findByParentTweetId(Long tweetId, Pageable pageable);
    Page<Tweet> findByParentTweetIdAndIdLessThan(Long tweetId, Long lastId, Pageable pageable);

    // select * from tweets where tweets.id < lastId order by tweets.id desc limit
    Page<Tweet> findAllByIdLessThan(Long lastId, Pageable pageable);

}
