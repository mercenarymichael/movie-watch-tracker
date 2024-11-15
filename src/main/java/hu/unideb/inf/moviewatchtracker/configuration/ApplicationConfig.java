package hu.unideb.inf.moviewatchtracker.configuration;

import hu.unideb.inf.moviewatchtracker.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final AccountRepository accountRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
    }
}
