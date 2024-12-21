package hu.unideb.inf.moviewatchtracker.controller;

import hu.unideb.inf.moviewatchtracker.data.AccountDto;
import hu.unideb.inf.moviewatchtracker.data.ExtendedMovieDetails;
import hu.unideb.inf.moviewatchtracker.entity.Account;
import hu.unideb.inf.moviewatchtracker.mapper.AccountMapper;
import hu.unideb.inf.moviewatchtracker.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper mapper;

    @GetMapping("/account")
    public AccountDto getAccount() {
        return mapper.accountToAccountDto(accountService.getAccount().get());
    }

    @PostMapping("/account")
    public void account(@RequestBody Account account) {
        accountService.registerAccount(account);
    }

    @PostMapping("/account/password")
    public void updatePassword(@RequestParam String newPassword) {
        accountService.updatePassword(newPassword);
    }

    @GetMapping("/account/watch_list")
    public List<ExtendedMovieDetails> getWatchList() {
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
