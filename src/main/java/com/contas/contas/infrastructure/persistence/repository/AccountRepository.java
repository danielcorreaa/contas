package com.contas.contas.infrastructure.persistence.repository;

import com.contas.contas.infrastructure.persistence.document.AccountDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<AccountDocument, ObjectId> {
    @Query("{active:true}")
    List<AccountDocument> findActiveAll();
}
