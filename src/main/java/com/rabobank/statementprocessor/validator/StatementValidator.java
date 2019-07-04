package com.rabobank.statementprocessor.validator;

import com.rabobank.statementprocessor.model.StatementInput;
import com.rabobank.statementprocessor.model.StatementRecord;
import com.rabobank.statementprocessor.model.ValidationResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatementValidator {

    public static List<ValidationResult> validate(StatementInput input) {
        return input.getInput()
                .parallelStream()
                .filter(record -> isReferenceUnique(input, record) || isEndBalanceCorrect(record))
                .map(StatementValidator::createValidationResult)
                .collect(Collectors.toList());
    }

    private static boolean isReferenceUnique(StatementInput input, StatementRecord record) {
        return Collections.frequency(input.getInput(), record) > 1;
    }

    private static boolean isEndBalanceCorrect(StatementRecord record) {
        return Double.valueOf(Double.sum(record.getStartBalance(), record.getMutation())).equals(record.getEndBalance());
    }

    private static ValidationResult createValidationResult(StatementRecord record) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setReference(record.getReference());
        validationResult.setDescription(record.getDescription());
        return validationResult;
    }
}
