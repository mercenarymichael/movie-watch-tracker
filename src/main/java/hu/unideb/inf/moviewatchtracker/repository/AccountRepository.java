package hu.unideb.inf.moviewatchtracker.repository;

import hu.unideb.inf.moviewatchtracker.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account getAccountById(int id);
    Optional<Account> findAccountByUsername(String username);

    boolean existsAccountByUsername(String username);
}