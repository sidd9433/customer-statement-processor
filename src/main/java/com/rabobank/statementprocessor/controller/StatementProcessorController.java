package com.rabobank.statementprocessor.controller;

import com.rabobank.statementprocessor.exception.StatementProcessException;
import com.rabobank.statementprocessor.model.StatementInput;
import com.rabobank.statementprocessor.service.StatementProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v1/")
public class StatementProcessorController {

    private StatementProcessorService statementProcessorService;

    @Autowired
    public StatementProcessorController(StatementProcessorService statementProcessorService) {
        this.statementProcessorService = statementProcessorService;
    }

    @PostMapping("process-statement")
    public ResponseEntity<StatementInput> handleCsrFile(@NotNull @RequestParam("file") MultipartFile file) throws StatementProcessException {
        return ResponseEntity.ok(statementProcessorService.execute(file));
    }
}