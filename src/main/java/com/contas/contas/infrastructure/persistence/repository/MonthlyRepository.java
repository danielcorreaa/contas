package com.contas.contas.infrastructure.persistence.repository;

import com.contas.contas.infrastructure.persistence.document.MonthlyDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MonthlyRepository extends MongoRepository<MonthlyDocument, String> {
    @Query("{user: ?0}")
    Page<MonthlyDocument> findAlByUser(String user, PageRequest pagination);
}
