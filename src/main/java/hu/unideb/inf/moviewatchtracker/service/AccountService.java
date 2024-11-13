package hu.unideb.inf.moviewatchtracker.service;

import hu.unideb.inf.moviewatchtracker.data.AccountDto;
import hu.unideb.inf.moviewatchtracker.data.MovieDto;
import hu.unideb.inf.moviewatchtracker.entity.Account;
import hu.unideb.inf.moviewatchtracker.mapper.AccountMapper;
import hu.unideb.inf.moviewatchtracker.mapper.MovieMapper;
import hu.unideb.inf.moviewatchtracker.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private MovieMapper movieMapper;

    public AccountDto getAccountById(int id) {
        return accountMapper.accountToAccountDto(accountRepository.getAccountById(id));
    }

    public void registerAccount(Account account) {
        accountRepository.save(account);
    }

    public List<MovieDto> getWatchList(Integer id) {
        Account account = accountRepository.getAccountById(id);
        return movieMapper.movieListToMovieDtoList(account.getMovies());
    }
}
