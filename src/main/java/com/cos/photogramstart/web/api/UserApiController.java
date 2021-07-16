package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomVlidationApiException;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMResDto;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    @PutMapping("/api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(@PathVariable long principalId, MultipartFile profileImageFile, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User user = userService.회원프로필사진변경(principalId, profileImageFile);
        principalDetails.setUser(user); //세션 변경
        return new ResponseEntity<>(new CMResDto<>(1,"프로필사진변경 성공",null),HttpStatus.OK);
    }

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable long pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<SubscribeDto> dto = subscribeService.구독리스트(principalDetails.getUser().getId(), pageUserId);
        return new ResponseEntity<>(new CMResDto<>(1,"구독자 정보 리스트 불러오기 성공", dto), HttpStatus.OK);
    }

    @PutMapping("/api/user/{id}")
    public CMResDto<?> update(
            @PathVariable long id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult, //꼭 @Valid 다음에 적어야한다.
            @AuthenticationPrincipal PrincipalDetails principalDetails){

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomVlidationApiException("유효성 검사 실패함", errorMap);
        }else {
            User user = userService.회원수정(id, userUpdateDto.toEntity());
            //정보 수정 후 세션정보 변경
            principalDetails.setUser(user);
            return new CMResDto<>(1, "회원수정완료", user); //응답시에 user의 모든 함수가 호출되고 JSON으로 파싱하여 응답한다.
        }
    }
}
