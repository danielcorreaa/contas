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
                monthly.getUpdateDate(), accountMapper.accountDocuments(monthly.getAccounts()));
    }

    public Monthly monthly(MonthlyDocument save) {
        return new Monthly(save.getId(), accountMapper.toAccount(save.getAccounts()));
    }

    public List<Monthly> toMonthlies(List<MonthlyDocument> all) {
        return all.stream().map(this::monthly).toList();
    }
}
