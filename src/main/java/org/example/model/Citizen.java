package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Citizen {
    private Date birthDate;
    private String firstName;
    private Long id;
    private String address;
    private String country;
}
