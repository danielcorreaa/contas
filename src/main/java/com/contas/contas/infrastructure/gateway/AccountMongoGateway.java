package com.contas.contas.infrastructure.gateway;

import com.contas.contas.application.gateway.AccountGateway;
import com.contas.contas.config.exceptions.NotFoundException;
import com.contas.contas.domain.Account;
import com.contas.contas.infrastructure.gateway.mapper.AccountMapper;
import com.contas.contas.infrastructure.persistence.document.AccountDocument;
import com.contas.contas.infrastructure.persistence.repository.AccountRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountMongoGateway implements AccountGateway {

    private AccountRepository accountRepository;
    private AccountMapper mapper;

    public AccountMongoGateway(AccountRepository accountRepository, AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public Account edit(Account account) {
        return null;
    }

    @Override
    public List<Account> saveAll(List<Account> account) {
        List<AccountDocument> accountDocuments = mapper.accountDocuments(account);
        var documents = accountRepository.saveAll(accountDocuments);
        return mapper.toAccount(documents);
    }

    @Override
    public List<Account> findAllActive(String user) {
        return mapper.toAccount(accountRepository.findActiveAll(user));
    }

    @Override
    public Account inactive(String id) {
        AccountDocument document =
                accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account not found"));
        document.setActive(Boolean.FALSE);
        return mapper.account(accountRepository.save(document));
    }

    @Override
    public List<Account> findAll(String user) {
        return mapper.toAccount(accountRepository.findAllByUser(user));
    }
}
