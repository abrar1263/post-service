package com.perfectsquare.postservice.controller;

import com.perfectsquare.common.model.dto.PSApiResponse;
import com.perfectsquare.postservice.model.dto.PostRequestDto;
import com.perfectsquare.postservice.model.dto.PostResponseDto;
import com.perfectsquare.postservice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
@AllArgsConstructor
public class PostController {

    PostService postService;

    @PostMapping("/insert-post")
    public PSApiResponse insertPost(@RequestBody PostRequestDto postRequestDto){
        String s = postService.insertPost(postRequestDto);
        return new PSApiResponse.Success(s);
    }

    @PutMapping("/update-post/{postId}")
    public PSApiResponse updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto){
        String s = postService.updatePost(postId, postRequestDto);
        return new PSApiResponse.Success(s);
    }

    @GetMapping("/get-all-post")
    public PSApiResponse getAllPost(){
        List<PostResponseDto> allPost = postService.getAllPost();
        return new PSApiResponse.Success(allPost);
    }

    @GetMapping("/get-post/{postId}")
    public PSApiResponse getPostById(@PathVariable Long postId){
        PostResponseDto postById = postService.getPostById(postId);
        return new PSApiResponse.Success(postById);
    }

    @DeleteMapping("/delete-post/{postId}")
    public PSApiResponse deletePost(@PathVariable Long postId){
        String s = postService.deletePost(postId);
        return new PSApiResponse.Success(s);
    }
}
