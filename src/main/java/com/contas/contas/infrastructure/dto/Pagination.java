package com.contas.contas.infrastructure.dto;

import com.contas.contas.domain.Monthly;

import java.util.List;

public class Pagination {

    private List<Monthly> monthlies;
    private Boolean next;
    private Long total;

    public List<Monthly> getMonthlies() {
        return monthlies;
    }

    public Boolean getNext() {
        return next;
    }

    public Long getTotal() {
        return total;
    }


    public static final class PaginationBuilder {
        private List<Monthly> monthlies;
        private Boolean next;
        private Long total;

        private PaginationBuilder() {
        }

        public static PaginationBuilder aPagination() {
            return new PaginationBuilder();
        }

        public PaginationBuilder monthlies(List<Monthly> monthlies) {
            this.monthlies = monthlies;
            return this;
        }

        public PaginationBuilder next(Boolean next) {
            this.next = next;
            return this;
        }

        public PaginationBuilder total(Long total) {
            this.total = total;
            return this;
        }

        public Pagination build() {
            Pagination pagination = new Pagination();
            pagination.next = this.next;
            pagination.monthlies = this.monthlies;
            pagination.total = this.total;
            return pagination;
        }
    }
}
