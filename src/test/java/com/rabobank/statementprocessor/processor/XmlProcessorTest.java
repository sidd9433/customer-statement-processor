package com.rabobank.statementprocessor.processor;

import com.rabobank.statementprocessor.model.StatementInput;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlProcessorTest {
    @Test
    public void processSuccess() {
        FileProcessor csvProcessor = new CsvProcessor();
        StatementInput input = csvProcessor.process(getClass().getResourceAsStream("/records.csv"));

        assertEquals(10, input.getInput().size());
    }
}
