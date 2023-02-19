package org.example.service;

import org.example.model.Citizen;
import org.example.model.Country;
import org.example.model.Currency;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReadService {

    public static List<Citizen> readCitizensFromFile(String pathToFile) {
        try {
            String citizensLines = Files.readString(Path.of(pathToFile));
            return Arrays.stream(citizensLines.split("\n")).skip(1).map(ReadService::parseString).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("something went wrong during citizen file parsing: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private static Citizen parseString(String line) {
        String[] cells = line.split(",");
        return Citizen.builder()
                .name(cells[0])
                .lastName(cells[1])
                .country(Country.valueOf(cells[2].toUpperCase()))
                .salary(Integer.valueOf(cells[3]))
                .currency(Currency.valueOf(cells[4]))
                .monthOfWork(Integer.valueOf(cells[5]))
                .isMale("Male".equals(cells[6]))
                .isDisabled("Yes\r".equals(cells[7]))
                .build();
    }

}
