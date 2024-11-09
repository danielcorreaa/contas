package com.contas.contas.application.gateway;

import com.contas.contas.domain.Monthly;
import com.contas.contas.infrastructure.dto.Pagination;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MonthlyGateway {

    Monthly save(Monthly monthly);
    Monthly edit(Monthly monthly);
    Pagination findAll(String user,int page, int size);
    Optional<Monthly> findById(String id);
}
