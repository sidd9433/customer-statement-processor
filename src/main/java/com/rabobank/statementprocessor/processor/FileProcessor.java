package com.rabobank.statementprocessor.processor;

import com.rabobank.statementprocessor.model.StatementInput;

import java.io.InputStream;

public interface FileProcessor {
    StatementInput process(InputStream inputStream);
}
