package com.example.bank.controller;

import com.example.bank.model.Account;
import com.example.bank.model.User;
import com.example.bank.service.BankService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ComponentScan
//@SpringBootTest
public class BankControllerTest {

    @MockBean
    BankService bankService;

    @InjectMocks
    BankController controller;

    @Autowired
    MockMvc mockMvc;
//    TestRestTemplate mockMvc;

    @Test
    void shouldGetAllUsers() throws Exception {
//        mockMvc.getForObject("/users", ResponseEntity.class);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getUsers"));
        verify(bankService).getAllUsers();
    }


    @Test
    void shouldGetAsekdUser() throws Exception {
        mockMvc.perform(get("/users/Bhawna")).andExpect(status().isOk());
        verify(bankService).getUser("Bhawna");
    }

    @Test
    void shouldAddGivenUser() throws Exception {
        User user = new User();
        user.setUserId("111");
        when(bankService.addUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).
                content("{\"userName\" : \"bhawna\"}")).andExpect(status().isOk());
    }

    @Test
    void shouldNotAddGivenUser_WhenNotAbleToCreateUser() throws Exception {
        User user = new User();
        when(bankService.addUser(any(User.class))).thenReturn(user);


        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\" : \"bhawna\"}"))
                .andExpect(status().isProcessing());
    }


    @Test
    void shouldGetAllAccount() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getAccounts"));
        verify(bankService).getAllAccounts();
    }

    @Test
    void shouldAddUserBankAccount() throws Exception {
        Account account = new Account();
        account.setAccountId("111");
        when(bankService.addAccount(any(Account.class))).thenReturn(account);

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"accountType\":\"saving\", \"userId\":\"Bhawna\", \"amount\": 500}"))
                .andExpect(status().isOk());
    }
}
