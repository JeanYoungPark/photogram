package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String caption;
    private String postImageUrl; //사진을 저장된 경로로

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    //이미지 좋아요
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Likes> likes;

    //이미지 댓글
    @OrderBy("id DESC")
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Comment> comments;

    private LocalDateTime createDate;

    @Transient //db에 컬럼이 만들어지지 않는다.
    private boolean likeState;

    @Transient
    private int likeCount;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}