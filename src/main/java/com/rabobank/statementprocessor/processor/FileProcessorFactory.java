package com.rabobank.statementprocessor.processor;

import com.rabobank.statementprocessor.enums.FileType;

public class FileProcessorFactory {
    public static FileProcessor getFileProcessor(FileType fileType) {
        switch (fileType) {
            case CSV:
                return new CsvProcessor();
            case XML:
                return new XmlProcessor();
            default:
                return null;
        }
    }
}
