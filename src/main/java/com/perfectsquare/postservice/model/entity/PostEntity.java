package com.perfectsquare.postservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "blog_post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity extends BaseEntity{

    @Id
    @SequenceGenerator(
            name = "post_seq",
            sequenceName = "post_seq",
            allocationSize = 1001
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_seq"
    )
    private Long postId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;



}
