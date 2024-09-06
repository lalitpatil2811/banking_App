package com.banking_app.service;

import com.banking_app.model.Account;
import com.banking_app.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    AccountRepository accountRepository;

    public void CreateAccount(Account account, HttpStatus created) {
        accountRepository.save(account);
    }

    public Account getAccountsBYId(long id, HttpStatus ok) {
        return accountRepository.
                findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account deposit(long id, double amount) {
        Account account = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not does not exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        return accountRepository.save(account);
    }

    public Account withdraw(long id, double amount) {
        Account account = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found so you cannot withdraw money"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        Double total = account.getBalance() - amount;
        account.setBalance(total);
        return accountRepository.save(account);

    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteBYid(long id) {
        Account account = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found "));
        accountRepository.deleteById(id);
    }

    public Account updateAccount(Account account) {
        Account account1 = accountRepository.findById(account.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return accountRepository.save(account);
    }


}
