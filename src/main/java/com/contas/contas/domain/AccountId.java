package com.contas.contas.domain;


public class AccountId {

    private AccountId() {
    }

    public static String getId(String name, String user){
        return String.format("%s--%s", name, user);
    }
}
