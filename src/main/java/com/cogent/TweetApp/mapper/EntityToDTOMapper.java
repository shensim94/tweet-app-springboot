package com.cogent.TweetApp.mapper;

import com.cogent.TweetApp.entity.Tweet;
import com.cogent.TweetApp.entity.User;
import com.cogent.TweetApp.payload.TweetDTO;
import com.cogent.TweetApp.payload.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityToDTOMapper {

    public UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setTweetCount(user.getTweets().size());
        dto.setLikeCount(user.getLikedTweets().size());
        return dto;
    }

    public TweetDTO toTweetDTO(Tweet tweet) {
        TweetDTO dto = new TweetDTO();
        dto.setId(tweet.getId());
        dto.setContent(tweet.getContent());
        dto.setUser(toUserDTO(tweet.getUser()));
        dto.setLikeCount(tweet.getLikeCount());
        dto.setReplyCount(tweet.getReplyCount());

        if (tweet.getParentTweet() != null) {
            dto.setParentTweetId(tweet.getParentTweet().getId());
        }
        dto.setCreatedAt(tweet.getCreatedAt());
        return dto;
    }

    public List<TweetDTO> toTweetDTOList(List<Tweet> tweets) {
        return tweets.stream()
                .map(this::toTweetDTO)
                .collect(Collectors.toList());
    }
}
