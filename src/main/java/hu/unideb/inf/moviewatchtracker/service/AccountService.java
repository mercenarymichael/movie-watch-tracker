package hu.unideb.inf.moviewatchtracker.service;

import hu.unideb.inf.moviewatchtracker.data.AccountDto;
import hu.unideb.inf.moviewatchtracker.data.ExtendedMovieDetails;
import hu.unideb.inf.moviewatchtracker.entity.Account;
import hu.unideb.inf.moviewatchtracker.mapper.AccountMapper;
import hu.unideb.inf.moviewatchtracker.mapper.MovieMapper;
import hu.unideb.inf.moviewatchtracker.repository.AccountRepository;
import hu.unideb.inf.moviewatchtracker.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final MovieMapper movieMapper;

    private final MovieRepository movieRepository;

    private final PasswordEncoder passwordEncoder;

    public AccountDto getAccountById(int id) {
        return accountMapper.accountToAccountDto(accountRepository.getAccountById(id));
    }

    public void registerAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional
    public List<ExtendedMovieDetails> getWatchList() {
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

    public void updatePassword(String newPassword) {
        Optional<Account> account = getAccount();
        if (account.isPresent()) {
            account.get().setPassword(passwordEncoder.encode(newPassword));
            accountRepository.save(account.get());
        }
    }
}
