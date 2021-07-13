package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WevMvcConfig implements WebMvcConfigurer { //web 설정 파일

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry
            .addResourceHandler("/upload/**") //jsp 페이지에서 다음과 같은 주소패턴세어 사용
            .addResourceLocations("file:///"+uploadFolder)
            .setCachePeriod(60*10*6) //1시간
            .resourceChain(true)
            .addResolver(new PathResourceResolver());
    }
}
