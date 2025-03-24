package com.cogent.TweetApp.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private int tweetCount;
    private int likeCount;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getTweetCount() {
        return tweetCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTweetCount(int tweetCount) {
        this.tweetCount = tweetCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
