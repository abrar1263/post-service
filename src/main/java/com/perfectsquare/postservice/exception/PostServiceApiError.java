package com.perfectsquare.postservice.exception;

import com.perfectsquare.common.exception.PSApiError;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public enum PostServiceApiError implements PSApiError {
    BAD_REQUEST(2001,"BAD Request."),
    INVALID_INPUT(2002,"Invalid Input."),
    UNDEFINED_POST_ERROR(2003,"An Error Occurred"),
    POST_NOT_FOUND(2003,"Post Not Found for given postid");



    final int code;
    final String message;
    @Setter
    @Getter
    Map<String,String> errorDetails;

    private PostServiceApiError(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public int getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
