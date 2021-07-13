package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomVlidationApiException;
import com.cos.photogramstart.handler.ex.CustomVlidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomVlidationException.class) //모든 exception은 여기로
    public String validationException(CustomVlidationException e){
        return Script.back(e.getErrorMap().toString());
    } //스크립트 응답

    @ExceptionHandler(CustomVlidationApiException.class)
    public ResponseEntity<CMResDto<?>> validationApiException(CustomVlidationApiException e){
        return new ResponseEntity<>(new CMResDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    } //데이터 응답, 상태코드 넣어주면 ajax fail로 이어진다.

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<CMResDto<?>> apiException(CustomApiException e){
        return new ResponseEntity<>(new CMResDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
