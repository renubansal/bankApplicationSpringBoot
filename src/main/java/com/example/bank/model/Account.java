package com.example.bank.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String accountId;

    private String accountType;

    private String userId;

    public String getAccountId() {
        return accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getUserId() {
        return userId;
    }
}


// If we don't implement getters in entity, values doesn't get saved in db-> check if this really happens