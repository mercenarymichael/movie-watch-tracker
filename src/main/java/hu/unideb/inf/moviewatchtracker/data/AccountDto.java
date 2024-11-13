package hu.unideb.inf.moviewatchtracker.data;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link hu.unideb.inf.moviewatchtracker.entity.Account}
 */
@Value
public class AccountDto {
    Integer id;
    String username;
    String email;
    String role;

}