package com.rabobank.statementprocessor.controller;

import com.rabobank.statementprocessor.exception.StatementProcessException;
import com.rabobank.statementprocessor.service.StatementProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v1/")
public class StatementProcessorController {

    private final Logger LOGGER = LoggerFactory.getLogger(StatementProcessorController.class);

    private StatementProcessorService statementProcessorService;

    @Autowired
    public StatementProcessorController(StatementProcessorService statementProcessorService) {
        this.statementProcessorService = statementProcessorService;
    }

    @PostMapping("process-statement")
    public ResponseEntity<?> handleCsrFile(@NotNull @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(statementProcessorService.execute(file));
    }

    @ExceptionHandler(StatementProcessException.class)
    public ResponseEntity<?> handleStatementProcessException(RuntimeException re) {
        LOGGER.info("Exception in process", re);
        return ResponseEntity.badRequest().body("Exception in process" + re.getMessage());
    }
}