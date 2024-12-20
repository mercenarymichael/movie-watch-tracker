package hu.unideb.inf.moviewatchtracker;

import hu.unideb.inf.moviewatchtracker.entity.Account;
import hu.unideb.inf.moviewatchtracker.entity.enums.Role;
import hu.unideb.inf.moviewatchtracker.repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieWatchTrackerApplication {

    @Autowired
    AccountRepository accountRepository;


    public static void main(String[] args) {
        SpringApplication.run(MovieWatchTrackerApplication.class, args);
    }

    @PostConstruct
    public void loadAdminAccount() {
        if (!accountRepository.existsAccountByUsername("admin")) {
            accountRepository.save(Account.builder()
                    .username("admin")
                    .email("admin@gmail.com")
                    .password("$2a$12$MqJAtnKbTXBi7zgLGEISqub8VcPaXKPKcw5NrA/vsvcLDPQsE6gs6")
                    .role(Role.ADMIN)
                    .build());
            System.out.println("Admin account initialized.");
        }
    }
}
