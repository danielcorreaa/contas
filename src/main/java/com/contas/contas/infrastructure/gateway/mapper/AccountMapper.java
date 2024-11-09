package com.contas.contas.infrastructure.gateway.mapper;

import com.contas.contas.domain.Account;
import com.contas.contas.infrastructure.persistence.document.AccountDocument;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountMapper {

    public AccountDocument accountDocument(Account account){
        return new AccountDocument( account.getId() ,
                account.getName(), account.getValor(), account.getCheck(), account.getActive(), account.getUser());
    }

    public List<AccountDocument> accountDocuments(List<Account> account){
        return account.stream().map(this::accountDocument).toList();
    }

    public Account account(AccountDocument accountDocument){
        return  new Account().copy(accountDocument.getId().toString(), accountDocument.getName(), accountDocument.getValor(), accountDocument.getCheck(), accountDocument.getActive());
    }
    public List<Account> toAccount(List<AccountDocument> documents) {
        return documents.stream().map(this::account).toList();
    }
}
