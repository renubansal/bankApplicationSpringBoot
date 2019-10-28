package com.example.bank.service;

import com.example.bank.model.Account;
import com.example.bank.model.User;
import com.example.bank.repository.AccountRepo;
import com.example.bank.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.util.Length;

import java.util.List;

@Component
public class BankService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountRepo accountRepo;

    public User getUser(String userName){
        User user = this.userRepo.getUserByUserName(userName);

        setAccountsOf(user);
        return user;
    }

    public List<User> getAllUsers(){
       List<User> users = userRepo.findAll();
        for (User user:users) {
            setAccountsOf(user);
        }
       return users;
    }

    private void setAccountsOf(User user) {
        List<Account> accounts = this.accountRepo.getAccountByUserId(user.getUserName());
        user.setAccounts(accounts);
    }

    public List<Account> getAllAccounts(){
       return accountRepo.findAll();
    }

    public User addUser(User user){
        return userRepo.save(user);
    }

    public Account addAccount(Account account){
        return accountRepo.save(account);
    }
}
