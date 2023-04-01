package org.example.service;

import org.example.model.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class CitizenServiceMock implements CitizenService {

    @Autowired
    private List<Citizen> citizens;

    @Override
    public List<Citizen> getAllCitizens() {
        return citizens;
    }

    @Override
    public List<Citizen> getCitizensByCountry() {
        return null;
    }

}
