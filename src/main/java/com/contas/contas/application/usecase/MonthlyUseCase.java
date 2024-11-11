package com.contas.contas.application.usecase;

import com.contas.contas.domain.Monthly;
import com.contas.contas.infrastructure.controller.dto.MonthlyRequest;
import com.contas.contas.infrastructure.dto.Pagination;
;
import java.util.Optional;

public interface MonthlyUseCase {

    Optional<Monthly> findById(String id);
    Monthly createAccountByMonthly(String id);
    Monthly edit(Monthly monthly);
    Pagination findAll(String user, int page, int size);

    Monthly checkAccount(MonthlyRequest request);
}
