package com.contas.contas.infrastructure.persistence.repository;

import com.contas.contas.infrastructure.persistence.document.AccountDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<AccountDocument, String> {
    @Query("{active:true, user: ?0}")
    List<AccountDocument> findActiveAll(String user);
    @Query("{user:?0}")
    List<AccountDocument> findAllByUser(String user);
}
