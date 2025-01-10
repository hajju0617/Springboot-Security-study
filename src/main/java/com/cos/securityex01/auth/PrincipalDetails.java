package com.cos.securityex01.auth;

import com.cos.securityex01.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/*
    시큐리티가 /login 주소로 요청이 들어오면 대신 로그인을 처리함.
    로그인 처리가 완료 되면 SecurityContext를 생성하고, 이 SecurityContext에 인증된 사용자의 Authentication 객체를 저장.
    SecurityContext는 HTTP 세션에 저장되며, 이를 통해 사용자가 로그인 상태를 유지할 수 있음.
    즉, 인증이 완료되면 인증 객체가 생성되고 인증 객체가 Security Context에 저장되고, Security Context Hodler에 Security Context가 존재.
    스프링 시큐리티에서는 SecurityContext 안에 Authentication 객체가 존재하는지의 유무를 체크해서 인증여부를 결정함.

    그렇기 때문에 SecurityContext 는 사용자가 재접속하더라도 인증 당시의 데이터가 보존이 되어 있어야 함.
    사용자가 인증처리가 완료된 후 다시 사이트를 접속했을 때 세션에 저장된 SecurityContext  를 꺼내어 와서 SecurityContextHolder 에 저장하게 되고
    이는 전역으로 SecurityContext를 참조할 수 있게 하는 원리가 됨.

    Authentication 안에 User 정보가 있어야 됨.
    여기서 User 타입은 UserDetails 타입 객체.
    Authentication 객체 안에 User정보는 UserDetails 타입 객체.
*/
public class PrincipalDetails implements UserDetails {      // UserDetails 인터페이스를 구현했으므로 PrincipalDetails 타입의 객체를 UserDetails 타입으로 다룰 수 있음.

    private User user;      // 포함관계(합성(Composition))

    public PrincipalDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {        // 유저의 권한을 리턴하는 메서드.
//        user.getRole();이 권한을 뜻하지만 얘는 String 타입, 해당 메서드는 반환 타입이 Collection<? extends GrantedAuthority>.
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {      // 계정 만료 여부. (true : 만료되지 않음, false : 만료됐음.)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {       // 계정 잠금 여부. (true : 잠겨있지 않음, false : 잠겨 있어서 접근 제한됨.)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {      // 사용자의 자격 증명이 유효한지 (true : 유효함, false : 유효하지 않음.)
        return true;                               // Ex) 일정 기간 동안 비밀번호를 변경하지 않아서 자격 증명이 만료된 경우.
    }

    @Override
    public boolean isEnabled() {                    // 계정이 활성화 여부. (true : 활성화, false : 비활성화.)
        return true;                                // Ex) User모델에 마지막 로그인 시점 필드를 추가하고, 해당 메서드에서 user.getLoginDate()로 들고와서
                                                    // 현재 시간 - 마지막 로그인 시간의 값이 특정 값을 넘었다면 return false. (즉 비활성화)
    }
}
