package com.hackathon.analyzer.transformer;

import com.hackathon.analyzer.transformer.model.LogStatement;

final class ModelToCsvConverter {

    private final static String CSV_VALUES_DELIMITER = ",";
    private final static String CSV_VALUES_QOUTE = "\"";

    private ModelToCsvConverter() {
    }

    public static String toCsv(LogStatement logStatement) {
        return CSV_VALUES_QOUTE + logStatement.getIp().toLowerCase().replace("\"", "\"\"") + CSV_VALUES_QOUTE + CSV_VALUES_DELIMITER
                + CSV_VALUES_QOUTE + logStatement.getHttpMethod().toLowerCase().replace("\"", "\"\"") + CSV_VALUES_QOUTE + CSV_VALUES_DELIMITER
                + CSV_VALUES_QOUTE + logStatement.getQuery().toLowerCase().replace("\"", "\"\"") + CSV_VALUES_QOUTE + CSV_VALUES_DELIMITER
                + CSV_VALUES_QOUTE + logStatement.getResponseCode().toLowerCase().replace("\"", "\"\"") + CSV_VALUES_QOUTE + CSV_VALUES_DELIMITER
                + CSV_VALUES_QOUTE + logStatement.getPath().toLowerCase().replace("\"", "\"\"") + CSV_VALUES_QOUTE;
    }
}
