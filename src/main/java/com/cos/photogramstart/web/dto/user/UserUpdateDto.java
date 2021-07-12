package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {

    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private String website;
    private String bio;
    private String phone;
    private String gender;

    //조금 위험함, 코드수정 필요
    public User toEntity(){
        return User.builder()
                .name(name) //이름을 기재 안했으면 문제 생긴다.
                .password(password) //패스워드를 기재 안했으면 문제생긴다.
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }

}
