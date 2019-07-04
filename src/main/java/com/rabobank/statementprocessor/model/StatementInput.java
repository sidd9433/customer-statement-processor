package com.rabobank.statementprocessor.model;


import java.util.List;

public class StatementInput {

    List<StatementRecord> input;

    public List<StatementRecord> getInput() {
        return input;
    }

    public void setInput(List<StatementRecord> input) {
        this.input = input;
    }
}
