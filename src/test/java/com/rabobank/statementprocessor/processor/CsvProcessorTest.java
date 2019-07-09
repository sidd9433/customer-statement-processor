package com.rabobank.statementprocessor.processor;

import com.rabobank.statementprocessor.exception.StatementProcessException;
import com.rabobank.statementprocessor.model.StatementInput;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvProcessorTest {

    @Test
    public void processSuccess() {
        FileProcessor csvProcessor = new CsvProcessor();
        StatementInput input = csvProcessor.process(getClass().getResourceAsStream("/records.csv"));

        assertEquals(10, input.getInput().size());
    }

    @Test(expected = StatementProcessException.class)
    public void processFailureWrongData() {
        FileProcessor csvProcessor = new CsvProcessor();
        csvProcessor.process(getClass().getResourceAsStream("/wrong_records.csv"));
    }

    @Test(expected = StatementProcessException.class)
    public void processFailureWrongFile() {
        FileProcessor csvProcessor = new CsvProcessor();
        csvProcessor.process(getClass().getResourceAsStream("/invalid.csv"));
    }
}
