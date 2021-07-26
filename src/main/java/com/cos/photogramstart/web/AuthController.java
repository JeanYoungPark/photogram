package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupRqDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor //의존성 주입 시 사용 (final이 걸린 변수 생성자 생성)
@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String singupForm(){
        return "auth/signup";
    }

    //csrf(cross site request )토큰 때문에 접근이 안됨
    //관련 내용 notion 확인
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupRqDto signupRqDto, BindingResult bindingResult){
        User user = signupRqDto.toEntity();
        User userEntity = authService.join(user);
        return "auth/signin";
    }
}
