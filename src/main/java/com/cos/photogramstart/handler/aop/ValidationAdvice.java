package com.cos.photogramstart.handler.aop;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomVlidationApiException;
import com.cos.photogramstart.handler.ex.CustomVlidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component //RestController, Service 모든 것들이 component를 상속해서 만들어져 있다.
@Aspect
public class ValidationAdvice {

    @Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))") //메서드 실행 전후 모두 동작한다.
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg: args){
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;

                if(bindingResult.hasErrors()){
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error: bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }

                    throw new CustomVlidationApiException("유효성 검사 실패함", errorMap);
                }

            }
        }

        //ex)
        //proceedingJoinPoint => profile 함수의 모든 곳에 접근할 수 있는 변수
        //profile 함수보다 먼저실행
        
        return proceedingJoinPoint.proceed(); //profile 함수가 실행됨
    }

    @Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg: args){
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;

                if(bindingResult.hasErrors()){
                    Map<String, String> errorMap = new HashMap<>();
                    for (FieldError error : bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomVlidationException("유효성 검사 실패함", errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }

}
