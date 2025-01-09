package com.cos.securityex01.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String email;

    private String role;            // ROLE_USER, ROLE_ADMIN

    @CreationTimestamp
    private Timestamp createDate;       // CreationTimestamp으로 인해 자동으로 값이 생성됨.
}
