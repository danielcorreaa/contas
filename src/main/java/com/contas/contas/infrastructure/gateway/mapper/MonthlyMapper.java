package com.contas.contas.infrastructure.gateway.mapper;

import com.contas.contas.domain.Monthly;
import com.contas.contas.infrastructure.persistence.document.MonthlyDocument;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MonthlyMapper {

    private AccountMapper accountMapper;

    public MonthlyMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public MonthlyDocument monthlyDocument(Monthly monthly) {
        return new MonthlyDocument(monthly.getId(),monthly.getCreatedDate(),
                monthly.getUpdateDate(), accountMapper.accountDocuments(monthly.getAccounts()), monthly.getUser());
    }

    public Monthly monthly(MonthlyDocument save) {
        return Monthly.copy(save.getId(), save.getCreatedDate(), save.getUpdateDate() ,accountMapper.toAccount(save.getAccounts()), save.getUser());
    }

    public List<Monthly> toMonthlies(List<MonthlyDocument> all) {
        return all.stream().map(this::monthly).toList();
    }
}
