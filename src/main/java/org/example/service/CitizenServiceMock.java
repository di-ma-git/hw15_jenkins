package org.example.service;

import org.example.model.Citizen;

import java.util.List;

public class CitizenServiceMock implements CitizenService {

    private List<Citizen> citizens;

    CitizenServiceMock(List<Citizen> citizens) {
        this.citizens = citizens;
    }

    @Override
    public List<Citizen> getAllCitizens() {
        return citizens;
    }

}
