package com.contas.contas.infrastructure.gateway;

import com.contas.contas.application.gateway.MonthlyGateway;
import com.contas.contas.domain.Monthly;
import com.contas.contas.infrastructure.dto.Pagination;
import com.contas.contas.infrastructure.gateway.mapper.MonthlyMapper;
import com.contas.contas.infrastructure.persistence.repository.MonthlyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MonthlyMongoGateway implements MonthlyGateway {

    private MonthlyRepository monthlyRepository;
    private MonthlyMapper monthlyMapper;

    public MonthlyMongoGateway(MonthlyRepository mongoRepository, MonthlyMapper monthlyMapper) {
        this.monthlyRepository = mongoRepository;
        this.monthlyMapper = monthlyMapper;
    }

    @Override
    public Monthly save(Monthly monthly) {
        var monthlyDocument = monthlyRepository.save(monthlyMapper.monthlyDocument(monthly));
        return monthlyMapper.monthly(monthlyRepository.save(monthlyDocument));
    }

    @Override
    public Monthly edit(Monthly monthly) {
        return null;
    }


    public Pagination findAll(String user,int page, int size) {
        var pagination = PageRequest.of(page,size);
        var all = monthlyRepository.findAlByUser(user, pagination);
        return Pagination
                .PaginationBuilder
                .aPagination()
                .monthlies(monthlyMapper.toMonthlies(all.getContent()))
                .next(all.hasNext())
                .total(all.getTotalElements())
                .build();
    }

    @Override
    public Optional<Monthly> findById(String id) {
        return monthlyRepository.findById(id).map( monthly -> monthlyMapper.monthly(monthly));
    }
}
