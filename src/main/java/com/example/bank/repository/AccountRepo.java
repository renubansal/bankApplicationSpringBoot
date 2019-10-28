package com.example.bank.repository;
import com.example.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, String > {

    @Query("SELECT a FROM Account a WHERE a.userId = :userId")
    public List<Account> getAccountByUserId(@Param("userId") String userId);
}
