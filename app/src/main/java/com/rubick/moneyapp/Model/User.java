package com.rubick.moneyapp.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable {

    private long id;

    private String name;

    private String username;

    private String email;

    private String reason;

    private String password;

    private String memberSince;

    private BigDecimal avaregeSpend;

    public User(String name, String username, String email, String password, String memberSince) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.memberSince = memberSince;
    }

    public User(String name, String username, String email, String reason, String password, String memberSince) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.reason = reason;
        this.password = password;
        this.memberSince = memberSince;
    }

    public User(long id, String name, String username, String email, String reason, String memberSince, BigDecimal avaregeSpend) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.reason = reason;
        this.memberSince = memberSince;
        this.avaregeSpend = avaregeSpend;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemberSince() {
        return this.memberSince;
    }

    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }

    public BigDecimal getAvaregeSpend() {
        return this.avaregeSpend;
    }

    public void setAvaregeSpend(BigDecimal avaregeSpend) {
        this.avaregeSpend = avaregeSpend;
    }
}
