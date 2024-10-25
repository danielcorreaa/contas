package com.contas.contas.infrastructure.controller.mapper;

import com.contas.contas.domain.Account;
import com.contas.contas.infrastructure.controller.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountApiMapper {

    public AccountDto toAccountDto(Account account) {
        return new AccountDto( account.getId(), account.getName(), account.getActive(), account.getValor(), account.getCheck());
    }
    public List<AccountDto> toAccountsDto(List<Account> accounts) {
        return accounts.stream().map( this::toAccountDto).toList();
    }
}
