package hu.unideb.inf.moviewatchtracker.controller;

import hu.unideb.inf.moviewatchtracker.data.AccountDto;
import hu.unideb.inf.moviewatchtracker.data.MovieApiDto;
import hu.unideb.inf.moviewatchtracker.entity.Account;
import hu.unideb.inf.moviewatchtracker.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    public AccountDto account(@RequestParam int id) {
        return accountService.getAccountById(id);
    }

    @PostMapping("/account")
    public void account(@RequestBody Account account) {
        accountService.registerAccount(account);
    }

    @GetMapping("/account/watch_list")
    public List<MovieApiDto> getWatchList() {
        return accountService.getWatchList();
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping("/account")
    public void deleteAccount(@RequestParam int id) {
        accountService.deleteAccount(id);
    }
}
