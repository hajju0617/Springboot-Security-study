package com.cos.securityex01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration              // 스프링 설정 클래스임 나타냄. (빈 등록 및 설정)
@EnableWebSecurity          // 시큐리티 활성화 및 보안 설정. (활성화 시) -> 스프링 시큐리티 필터(SecurityConfig)가 스프링 필터체인에 등록됨.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)   // securedEnabled = true : @Secured 에노테이션 활성화. prePostEnabled = true : @PreAuthorize 에노테이션 활성화.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/user/**").authenticated()    // 해당 주소는 로그인이 필요.
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")      // 해당 주소로 접속 시 로그인뿐만 아니라 권한(admin, manager)도 필요함.
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")                                   // 해당 주소는 admin 권한만 들어갈 수 있음.
                .anyRequest().permitAll()      // 그외 다른 요청은 전부 허용.
                .and().formLogin().loginPage("/loginForm")                       // 권한이 없는 주소로 요청을 하면 로그인 페이지로 이동되게 설정.
                .loginProcessingUrl("/login")                                      // '/login' 주소가 호출되면 시큐리티가 대신 로그인을 처리함. -> 그래서 컨트롤러에 login 메서드가 필요없음.
                .defaultSuccessUrl("/");                                            // 로그인 성공 시 메인 페이지로 이동.
    }

    @Bean    // 해당 메서드의 리턴되는 객체를 IoC로 등록해줌.
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
