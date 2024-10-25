package com.contas.contas.infrastructure.persistence.repository;

import com.contas.contas.infrastructure.persistence.document.MonthlyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonthlyRepository extends MongoRepository<MonthlyDocument, String> {
}
