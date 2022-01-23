package com.rubick.moneyapp.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Expense implements Serializable {

    private long id;
    private long userId;
    private BigDecimal value;
    private Type type;
    private BigDecimal mensalPercentage;
    private String description;
    private TypeOfExpense typeOfExpenseType;
    private String date;

    public Expense(long userId, BigDecimal value, Type type, String description, TypeOfExpense typeOfExpenseType, String date) {
        this.userId = userId;
        this.value = value;
        this.type = type;
        this.description = description;
        this.typeOfExpenseType = typeOfExpenseType;
        this.date = date;
    }

    public Expense(long userId, BigDecimal value, Type type, TypeOfExpense typeOfExpenseType, String date) {
        this.userId = userId;
        this.value = value;
        this.type = type;
        this.typeOfExpenseType = typeOfExpenseType;
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

    public BigDecimal getMensalPercentage() {
        return mensalPercentage;
    }

    public void setMensalPercentage(BigDecimal mensalPercentage) {
        this.mensalPercentage = mensalPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeOfExpense getTypeOfExpenseType() {
        return typeOfExpenseType;
    }

    public void setTypeOfExpenseType(TypeOfExpense typeOfExpenseType) {
        this.typeOfExpenseType = typeOfExpenseType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}