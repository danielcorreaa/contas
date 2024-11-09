package com.contas.contas.domain;

import java.math.BigDecimal;

public class Account {

    private String id;

    private String name;

    private BigDecimal valor;
    private Boolean check;
    private Boolean active;
    private String user;

    public Account(String name, String user) {
        this.name = name;
        this.user = user;
        this.id = AccountId.getId(name, user);
        this.active = Boolean.TRUE;
    }

    public void inactiveAccount(){
        this.active = Boolean.FALSE;
    }

    public void checkAccount(Boolean check, BigDecimal valor){
        this.check = check;
        this.valor = valor;
    }

    public Account() {}

    public Account copy(String id, String name, BigDecimal valor, Boolean check, Boolean active){
        this.id = id;
        this.name = name;
        this.valor = valor;
        this.check = check;
        this.active = active;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Boolean getCheck() {
        return check;
    }

    public Boolean getActive() {
        return active;
    }

    public String getUser() {
        return user;
    }
}
