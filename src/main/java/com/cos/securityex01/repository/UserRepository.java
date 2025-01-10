package com.cos.securityex01.repository;

import com.cos.securityex01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 기본적인 CRUD 메서드를 JpaRepository가 들고있음
public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy 까지는 규칙, 그 뒤에 있는 Username은 문법.
    // 즉, SELECT * FROM user WHERE username = ? 가 호출이 됨. (?에는 파라미터에 있는 username이 삽입됨.)
    User findByUsername(String username);
}
