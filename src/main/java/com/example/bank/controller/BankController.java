package com.example.bank.controller;

import com.example.bank.model.Account;
import com.example.bank.model.User;
import com.example.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@Configurable
@EnableAutoConfiguration
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping(path = "/users")
    public ResponseEntity<Object> getUsers() {
        List<User> users = bankService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/users/{userName}")
    public ResponseEntity<Object> getUser(@PathVariable(name = "userName") String userName) {

        System.out.println("userName................." + userName);
        User user = bankService.getUser(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "/users", consumes = "application/json")
    public ResponseEntity<Object> addUser(@RequestBody User user) {

        User userAdded = bankService.addUser(user);
        if (userAdded.getUserId().equals(null)) {
            return new ResponseEntity<>(user, HttpStatus.PROCESSING);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/accounts")
    public ResponseEntity<Object> getAccounts() {
        List<Account> accounts = bankService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping(path = "/accounts", consumes = "application/json")
    public ResponseEntity<Object> addAccount(@RequestBody Account account) {

        Account accountAdded = bankService.addAccount(account);
        if (accountAdded.getAccountId().equals(null)) {
            return new ResponseEntity<>(accountAdded, HttpStatus.PROCESSING);
        }
        return new ResponseEntity<>(accountAdded, HttpStatus.OK);
    }

    @PatchMapping(path = "accounts/{fromAccountId}/transfer/{toAccountId}/{amountWithdraw}")
    public ResponseEntity<Object> transferMoney(@PathVariable(name = "fromAccountId") String fromAccountId,
                                                @PathVariable(name = "toAccountId") String toAccountId,
                                                @PathVariable(name = "amountWithdraw") float amountWithdraw) {
        User user = bankService.transferMoney(fromAccountId, toAccountId, amountWithdraw);
        if (user.equals(null)) {
            return new ResponseEntity<>(user, HttpStatus.PROCESSING);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
