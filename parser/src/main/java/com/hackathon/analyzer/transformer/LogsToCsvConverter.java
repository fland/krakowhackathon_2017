package com.hackathon.analyzer.transformer;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public final class LogsToCsvConverter {

    private final String outputFilePath;
    private final String inputFolderPath;
    private final LogsParser logsParser;

    public LogsToCsvConverter(String outputFilePath, String inputFolderPath) {
        this.outputFilePath = outputFilePath;
        this.inputFolderPath = inputFolderPath;
        logsParser = new LogsParser();
    }

    public void convert() throws IOException {
        File outputFile = new File(outputFilePath);
        FileUtils.write(outputFile, "", "UTF8", false);
        File inputDirectory = new File(inputFolderPath);
        FileUtils.iterateFiles(inputDirectory, new String[]{}, false)
                .forEachRemaining(file -> transformAndWrite(file, outputFile));
    }

    private void transformAndWrite(File inputFile, File outputFile) {
        try {
            List<String> lines = Files.lines(inputFile.toPath())
                    .map(logsParser::mapLogString)
                    .map(ModelToCsvConverter::toCsv)
                    .collect(Collectors.toList());
            FileUtils.writeLines(outputFile, lines, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
