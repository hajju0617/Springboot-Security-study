package com.cos.securityex01.auth;

import com.cos.securityex01.model.User;
import com.cos.securityex01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
    시큐리티 설정에서 .loginProcessingUrl("/login")으로 해놨기 때문에
    /login 요청이 오면 자동으로 UserDetailsService타입으로 IoC되어 있는 loadUserByUsername 메서드가 실행됨.
*/
@Service    // 빈등록.
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);
        User userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            System.out.println("userEntity = " + userEntity);
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
