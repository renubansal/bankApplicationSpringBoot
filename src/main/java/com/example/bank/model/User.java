package com.example.bank.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
