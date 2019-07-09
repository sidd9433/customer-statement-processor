package com.rabobank.statementprocessor.validator;

import com.rabobank.statementprocessor.model.StatementInput;
import com.rabobank.statementprocessor.model.StatementRecord;
import com.rabobank.statementprocessor.model.ValidationResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatementValidator {

    private StatementValidator() {
        throw new IllegalStateException("Utility class, not meant to be instantiated");
    }

    public static List<ValidationResult> validate(StatementInput input) {
        return input.getInput()
                .parallelStream()
                .filter(record -> isReferenceNotUnique(input, record) || isEndBalanceNotCorrect(record))
                .map(StatementValidator::createValidationResult)
                .collect(Collectors.toList());
    }

    private static boolean isReferenceNotUnique(StatementInput input, StatementRecord record) {
        return Collections.frequency(input.getInput(), record) > 1;
    }

    private static boolean isEndBalanceNotCorrect(StatementRecord record) {
        return !record.getStartBalance().add(record.getMutation()).equals(record.getEndBalance());
    }

    private static ValidationResult createValidationResult(StatementRecord record) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setReference(record.getReference());
        validationResult.setDescription(record.getDescription());
        return validationResult;
    }
}
