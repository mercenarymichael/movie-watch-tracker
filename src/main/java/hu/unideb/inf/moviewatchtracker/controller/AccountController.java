package hu.unideb.inf.moviewatchtracker.controller;

import hu.unideb.inf.moviewatchtracker.data.AccountDto;
import hu.unideb.inf.moviewatchtracker.data.MovieDto;
import hu.unideb.inf.moviewatchtracker.entity.Account;
import hu.unideb.inf.moviewatchtracker.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
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
    public List<MovieDto> getWatchList(@RequestParam Integer id) {
        return accountService.getWatchList(id);
    }
}
