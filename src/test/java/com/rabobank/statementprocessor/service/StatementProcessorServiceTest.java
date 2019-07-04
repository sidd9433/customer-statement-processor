package com.rabobank.statementprocessor.service;

import com.rabobank.statementprocessor.model.StatementOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatementProcessorServiceTest {

    @Mock
    MultipartFile multipartFile;
    private StatementProcessorService service = new StatementProcessorService();

    @Test
    public void executeSuccess() throws IOException {
        when(multipartFile.getContentType()).thenReturn("text/csv");
        when(multipartFile.getInputStream()).thenReturn(getClass().getResourceAsStream("/records.csv"));
        StatementOutput output = service.execute(multipartFile);
        assertEquals(3, output.getResult().size());
    }
}
