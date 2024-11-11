package hu.unideb.inf.moviewatchtracker.mapper;

import hu.unideb.inf.moviewatchtracker.data.AccountDto;
import hu.unideb.inf.moviewatchtracker.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto accountToAccountDto(Account account);
    Account accountDtoToAccount(AccountDto accountDto);

    List<AccountDto> accountListToAccountDtoList(List<Account> accountList);
    List<Account> accountDtoListToAccountList(List<AccountDto> accountDtoList);
}
