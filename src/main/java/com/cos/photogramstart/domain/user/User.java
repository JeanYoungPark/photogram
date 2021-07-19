package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String website; //웹 사이트

    private String bio; //자기소개

    @Column(nullable = false)
    private String email;

    private String phone;

    private String gender;

    private String profileImageUrl; //사진

    private String role; //권한

    //나는 연관관계의 주인이 아니다. 양방향 매핑
    //Lazy = user id로 등록된 image를 항상 가져오지 말고 getimage()를 호출할 때만 가져오도록
    //eager = user id로 등록된 image를 join하여 가져오도록
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"}) //무한참조때문에 사용
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist //db에 insert 되기 전에 실행된다.
    public void CreateDate(){
        this.createDate = LocalDateTime.now();
    }

}
