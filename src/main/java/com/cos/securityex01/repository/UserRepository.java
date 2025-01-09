package com.cos.securityex01.repository;

import com.cos.securityex01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
