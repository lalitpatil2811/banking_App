package com.banking_app.controller;

import com.banking_app.model.Account;
import com.banking_app.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/sbi")
public class AccountController {
    AccountService accountService;

    @PostMapping("/Add")
    public void createAccount(@RequestBody Account account) {
        accountService.CreateAccount(account, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountsBYId(id,HttpStatus.OK);
    }
    @PutMapping("/{id}/deposit")
    public Account deposit(@PathVariable long id, @RequestBody Map<String,Double> request) {
        return accountService.deposit(id, request.get("amount"));
    }


    @PutMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable long id, @RequestBody Map<String,Double> request) {
        return accountService.withdraw(id,request.get("amount"));

    }
    @GetMapping("/allAccount")
    public List<Account> getAllAccount(){
        return accountService.getAllAccounts();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAccountBYId(@PathVariable long id) {
        accountService.deleteBYid(id);
    }
   @PutMapping("/update")
    public Account updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }
}
