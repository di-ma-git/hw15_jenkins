package org.example.report;

import org.example.model.Citizen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TextReportWriter implements ReportWriter {
    private Path filePath;

    public TextReportWriter(String folder) {
        filePath = new File(folder).toPath();
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeReport(List<String> lines) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : lines) {
                stringBuilder.append(line + "\n");
            }
            Files.write(filePath, stringBuilder.toString().getBytes(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file!");
        }
    }
}
