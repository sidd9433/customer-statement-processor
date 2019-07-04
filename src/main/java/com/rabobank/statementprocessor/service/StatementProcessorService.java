package com.rabobank.statementprocessor.service;

import com.rabobank.statementprocessor.enums.FileType;
import com.rabobank.statementprocessor.exception.StatementProcessException;
import com.rabobank.statementprocessor.model.StatementInput;
import com.rabobank.statementprocessor.processor.FileProcessorFactory;
import com.rabobank.statementprocessor.util.ThrowingFunction;
import com.rabobank.statementprocessor.util.ThwringSupplier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Component
public class StatementProcessorService {

    public StatementInput execute(MultipartFile file) throws StatementProcessException {

        return Optional.of(file)
                .map(MultipartFile::getContentType)
                .map(FileType::get)
                .map(FileProcessorFactory::getFileProcessor)
                .map(ThrowingFunction.unchecked(fileProcessor -> fileProcessor.process(file.getInputStream())))
                .orElseThrow(ThwringSupplier.unchecked(() -> new StatementProcessException("Can't read the data")));
    }
}
