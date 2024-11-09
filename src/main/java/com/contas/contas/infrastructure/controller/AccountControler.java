package com.contas.contas.infrastructure.controller;

import com.contas.contas.application.gateway.AccountGateway;
import com.contas.contas.application.usecase.AccountUseCase;
import com.contas.contas.application.usecase.interactor.AccountInteractorUseCase;
import com.contas.contas.config.response.Result;
import com.contas.contas.domain.Account;
import com.contas.contas.infrastructure.controller.dto.AccountDto;
import com.contas.contas.infrastructure.controller.dto.AccountRequest;
import com.contas.contas.infrastructure.controller.mapper.AccountApiMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("account")
@CrossOrigin
public class AccountControler {

    private final AccountGateway accountGateway;
    private final AccountApiMapper accountApiMapper;

    public AccountControler(AccountGateway accountGateway, AccountApiMapper accountApiMapper) {
        this.accountGateway = accountGateway;
        this.accountApiMapper = accountApiMapper;
    }


    @PostMapping
    public ResponseEntity<Result<List<AccountDto>>> insert(@RequestBody AccountRequest request){
        AccountUseCase accountUseCase  = new AccountInteractorUseCase(accountGateway);
        var accounts =  accountApiMapper.toAcconts(request);
        var accountsResponse = accountUseCase.saveAll(accounts);
        return ResponseEntity.ok(Result.ok(accountApiMapper.toAccountsDto(accountsResponse)));
    }

    @PutMapping("inactive/{id}")
    public ResponseEntity<Result<AccountDto>> inactive(@PathVariable String id){
        AccountUseCase accountUseCase  = new AccountInteractorUseCase(accountGateway);
        var accountsResponse =  accountUseCase.inactive(id);
        return ResponseEntity.ok(Result.ok(accountApiMapper.toAccountDto(accountsResponse)));
    }

    @GetMapping("/{active}")
    public ResponseEntity<Result<List<AccountDto>>> findAll(
            @Parameter(hidden = true)
            @RequestHeader(value = "user", required = false) String user, @PathVariable Boolean active){
        AccountUseCase accountUseCase  = new AccountInteractorUseCase(accountGateway);
        List<Account> accountsResponse = null;
        if(Boolean.TRUE.equals(active)){
            accountsResponse = accountUseCase.findAllActiveByUser(user);
        } else {
            accountsResponse = accountUseCase.findAllByUser(user);
        }

        return ResponseEntity.ok(Result.ok(accountApiMapper.toAccountsDto(accountsResponse)));
    }

}
