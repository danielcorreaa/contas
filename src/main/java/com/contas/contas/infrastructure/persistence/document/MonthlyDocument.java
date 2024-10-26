package com.contas.contas.infrastructure.persistence.document;

import com.contas.contas.config.exceptions.BusinessException;
import com.contas.contas.domain.MonthlyId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "monthly")
public class MonthlyDocument {

    @Id
    private String id;
    private LocalDate createdDate;
    private LocalDate updateDate;
    private List<AccountDocument> accounts;

}
