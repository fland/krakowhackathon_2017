package com.hackathon.analyzer.transformer;

import com.hackathon.analyzer.transformer.model.LogStatement;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ModelToCsvConverterTest {

    @Test
    public void testConversion() {
        LogStatement logStatement = LogStatement.builder()
                .date(new Date())
                .httpMethod("GET")
                .ip("12.13.14.15")
                .path("/some/Path")
                .query("?SELECT\"*$%/\\")
                .responseCode("404")
                .build();

        String actualCsvRecord = ModelToCsvConverter.toCsv(logStatement);
        assertEquals("\"12.13.14.15\",\"get\",\"?select\"\"*$%/\\\",\"404\",\"/some/path\"", actualCsvRecord);
    }

}