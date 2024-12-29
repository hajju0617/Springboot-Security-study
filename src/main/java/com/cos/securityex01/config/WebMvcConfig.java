package com.cos.securityex01.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        MustacheViewResolver resolver = new MustacheViewResolver();
        // 뷰리졸버 머스테치를 재설정할 수 있음.

        resolver.setCharset("UTF-8");                           // 우리가 만든 뷰의 인코딩은 UTF-8
        resolver.setContentType("text/html;charset=UTF-8");     // 우리가 주는 파일은 html(UTF-8)
        resolver.setPrefix("classpath:/templates/");            // classpath:는 프로젝트 경로.
        resolver.setSuffix(".html");                            // .html을 만들어도 머스테치로 인식.
        registry.viewResolver(resolver);                        // 매개변수의 레지스터로 뷰리졸버를 등록.
    }
}
