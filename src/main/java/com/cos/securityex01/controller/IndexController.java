package com.cos.securityex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller     // view 반환.
public class IndexController {
    @GetMapping({"", "/"})
    public String index() {
        return "index";     // src/main/resources/templates/index.mustache
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager() {
        return "manager";
    }

    @GetMapping("/login")           // 시큐리티가 해당 URL은 낚아채버림.  -> SecurityConfig 파일 생성 후 작동 안 함.
    @ResponseBody
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    @ResponseBody
    public String join() {
        return "join";
    }

    @GetMapping("/joinProc")
    @ResponseBody
    public  String joinProc() {
        return "회원가입이 완료되었음.";
    }
}
