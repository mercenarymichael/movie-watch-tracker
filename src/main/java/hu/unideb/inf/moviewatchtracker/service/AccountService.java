package hu.unideb.inf.moviewatchtracker.service;

import hu.unideb.inf.moviewatchtracker.data.AccountDto;
import hu.unideb.inf.moviewatchtracker.data.MovieApiDto;
import hu.unideb.inf.moviewatchtracker.entity.Account;
import hu.unideb.inf.moviewatchtracker.mapper.AccountMapper;
import hu.unideb.inf.moviewatchtracker.mapper.MovieMapper;
import hu.unideb.inf.moviewatchtracker.repository.AccountRepository;
import hu.unideb.inf.moviewatchtracker.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;

    public AccountDto getAccountById(int id) {
        return accountMapper.accountToAccountDto(accountRepository.getAccountById(id));
    }

    public void registerAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional
    public List<MovieApiDto> getWatchList() {
        Optional<Account> account = getAccount();
        return account.map(value -> movieMapper.movieListToMovieApiDtoList(value.getMovies())).orElse(null);
    }

    public Optional<Account> getAccount() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            return accountRepository.findAccountByUsername(authentication.getName());
        }
        else return Optional.empty();
    }

    public List<AccountDto> getAllAccounts() {
        return accountMapper.accountListToAccountDtoList(accountRepository.findAll());
    }

    public void deleteAccount(int id) {
        Optional<Account> account = accountRepository.findById(id);
        account.ifPresent(accountRepository::delete);
    }
}
