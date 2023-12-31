package com.perfectsquare.postservice.service;

import com.perfectsquare.common.exception.PSApiException;
import com.perfectsquare.common.mapper.MapperUtil;
import com.perfectsquare.postservice.exception.PostServiceApiError;
import com.perfectsquare.postservice.model.dto.PostRequestDto;
import com.perfectsquare.postservice.model.dto.PostResponseDto;
import com.perfectsquare.postservice.model.entity.PostEntity;
import com.perfectsquare.postservice.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    PostRepository postRepository;

    MapperUtil mapperUtil;

    public String insertPost(PostRequestDto postRequestDto){
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postRequestDto.getTitle());
        postEntity.setContent(postRequestDto.getContent());
        postEntity.setCreatedBy("ADMIN");
        PostEntity save = postRepository.save(postEntity);
        return "Post Created with Post Id : "+save.getPostId();
    }
    public String updatePost(Long postId, PostRequestDto postRequestDto){
        PostEntity byId = postRepository.findById(postId).orElseThrow(()-> new PSApiException(PostServiceApiError.POST_NOT_FOUND));
        byId.setTitle(postRequestDto.getTitle());
        byId.setContent(postRequestDto.getContent());
        postRepository.save(byId);
        return "Post Updated";
    }

    public List<PostResponseDto> getAllPost(){
        List<PostEntity> all = postRepository.findAll();
        return mapperUtil.mapSourceToTargetClass(all, PostResponseDto.class);
    }

    public PostResponseDto getPostById(Long postId){
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new PSApiException(PostServiceApiError.POST_NOT_FOUND));
        return mapperUtil.mapSourceToTargetClass(postEntity, PostResponseDto.class);
    }

    public String  deletePost(Long postId){
        postRepository.deleteById(postId);
        return "Post Deleted Successfully";
    }
}
