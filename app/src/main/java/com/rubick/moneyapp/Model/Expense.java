package com.rubick.moneyapp.Model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Expense implements Serializable {

    private long id;
    private long userId;
    private BigDecimal value;
    private Type type;
    private BigDecimal monthlyPercentage;
    private String description;
    private TypeOfExpense typeOfExpense;
    private String date;

    public Expense(long userId, BigDecimal value, Type type, String description, TypeOfExpense typeOfExpense, String date) {
        this.userId = userId;
        this.value = value;
        this.type = type;
        this.description = description;
        this.typeOfExpense = typeOfExpense;
        this.date = date;
    }

    public Expense(long userId, BigDecimal value, Type type, TypeOfExpense typeOfExpense, String date) {
        this.userId = userId;
        this.value = value;
        this.type = type;
        this.typeOfExpense = typeOfExpense;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getMonthlyPercentage() {
        return monthlyPercentage;
    }

    public void setMonthlyPercentage(BigDecimal monthlyPercentage) {
        this.monthlyPercentage = monthlyPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeOfExpense getTypeOfExpense() {
        return typeOfExpense;
    }

    public void setTypeOfExpense(TypeOfExpense typeOfExpense) {
        this.typeOfExpense = typeOfExpense;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}