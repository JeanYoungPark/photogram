package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomVlidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMResDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomVlidationException.class) //모든 exception은 여기로
    public String validationException(CustomVlidationException e){
        //우선 스트링으로 응답 이후 ajax로 변경할 예정
        return Script.back(e.getErrorMap().toString());
    }

}
