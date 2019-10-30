package com.example.bank.service;

import com.example.bank.model.Account;
import com.example.bank.model.User;
import com.example.bank.repository.AccountRepo;
import com.example.bank.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {
    UserRepo userRepo;

    AccountRepo accountRepo;

    @Autowired
    public BankService(UserRepo userRepo, AccountRepo accountRepo) {
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
    }

    public User getUser(String userName) {
        System.out.println("user repo......" + this.userRepo);
        User user = this.userRepo.getUserByUserName(userName);

        setAccountsOf(user);
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            setAccountsOf(user);
        }
        return users;
    }

    private void setAccountsOf(User user) {
        List<Account> accounts = this.accountRepo.getAccountByUserId(user.getUserName());
        user.setAccounts(accounts);
    }

    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public Account addAccount(Account account) {
        return accountRepo.save(account);
    }

    public User transferMoney(String fromAccountId, String toAccountId, float amountWithdraw) {
        Account fromAccount = this.accountRepo.getAccountByAccountId(fromAccountId);
        Account toAccount = this.accountRepo.getAccountByAccountId(toAccountId);

        float remainingAmountInFromAccount = fromAccount.getAmount() - amountWithdraw;
        if (remainingAmountInFromAccount <= 0) {
            return null;
        }

        User fromAccountUser = this.userRepo.getUserByUserName(fromAccount.getUserId());

        fromAccount.setAmount(remainingAmountInFromAccount);
        toAccount.setAmount(toAccount.getAmount() + amountWithdraw);

        this.accountRepo.save(fromAccount);
        this.accountRepo.save(toAccount);

        fromAccountUser.setAccounts(new ArrayList<Account>(){{add(fromAccount);}});
        return fromAccountUser;
    }
}
