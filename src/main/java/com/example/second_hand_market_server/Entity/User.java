package com.example.second_hand_market_server.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table("users") // 这是正确的，但请注意这个注解是 Spring Data JDBC 的
public class User {
    @Id
    private Long id;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("is_admin")
    private Boolean isAdmin; // 推荐使用 Java 的命名约定

    @Column("user_name")
    private String userName; // 同上
}
