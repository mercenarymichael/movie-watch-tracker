package hu.unideb.inf.moviewatchtracker.repository;

import hu.unideb.inf.moviewatchtracker.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account getAccountById(int id);
}