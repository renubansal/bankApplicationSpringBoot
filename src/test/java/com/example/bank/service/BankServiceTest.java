package com.example.bank.service;

import com.example.bank.model.User;
import com.example.bank.repository.AccountRepo;
import com.example.bank.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BankServiceTest {

    @Mock
    AccountRepo accountRepo;

    @Mock
    UserRepo userRepo;

    @InjectMocks
    BankService bankService;

    @Test
    void shouldGetUser() {
        User user = new User();
        user.setUserId("1");
        user.setUserName("Bhawna");
        System.out.println("user repo...in test..." + this.userRepo);
        when(userRepo.getUserByUserName("Bhawna")).thenReturn(user);

        assertNotNull(bankService.getUser("Bhawna"));
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getAllAccounts() {
    }

    @Test
    void addUser() {
    }

    @Test
    void addAccount() {
    }

    @Test
    void transferMoney() {
    }
}