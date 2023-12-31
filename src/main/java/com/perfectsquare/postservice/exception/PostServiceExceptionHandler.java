package com.perfectsquare.postservice.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.perfectsquare.common.exception.PSApiException;
import com.perfectsquare.common.model.dto.PSApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class PostServiceExceptionHandler {

    @ExceptionHandler(PSApiException.class)
    public PSApiResponse apiException(PSApiException exception){
        exception.printStackTrace();
        return new PSApiResponse.Error(exception);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, JsonProcessingException.class})
    public PSApiResponse httpMessageNotReadableException(Exception exception){
        exception.printStackTrace();
        return new PSApiResponse.Error(PostServiceApiError.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public PSApiResponse httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception){
        exception.printStackTrace();
        return new PSApiResponse.Error(PostServiceApiError.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<PSApiResponse> httpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException exception){
        exception.printStackTrace();
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<PSApiResponse>(new PSApiResponse.Error(PostServiceApiError.BAD_REQUEST),headers, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public PSApiResponse methodArgumentNotValidException(MethodArgumentNotValidException exception){
        exception.printStackTrace();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Map<String,String> fieldErrorMap = new HashMap<String,String>();
        fieldErrors.forEach(fieldError -> fieldErrorMap.put(fieldError.getField(),fieldError.getDefaultMessage()));
        PostServiceApiError postServiceApiError = PostServiceApiError.INVALID_INPUT;
        postServiceApiError.setErrorDetails(fieldErrorMap);
        return new PSApiResponse.Error(postServiceApiError);
    }

    @ExceptionHandler(Exception.class)
    public PSApiResponse globalExceptionHandler(Exception exception){
        exception.printStackTrace();
        return new PSApiResponse.Error(PostServiceApiError.UNDEFINED_POST_ERROR);
    }
}
