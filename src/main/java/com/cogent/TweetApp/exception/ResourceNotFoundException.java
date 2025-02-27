package com.cogent.TweetApp.exception;

public class ResourceNotFoundException extends RuntimeException{

    // post not found with postId:100
    private String resourceName;

    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
