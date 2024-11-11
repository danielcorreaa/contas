package com.contas.contas.infrastructure.persistence.document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "account")
public class AccountDocument {

    @Id
    private String id;
    private String name;

    private BigDecimal valor;
    private Boolean check;
    private Boolean active;
    private String user;


}
