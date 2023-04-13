package org.example.controller;

import org.example.model.Citizen;
import org.example.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController

public class CitizenController {

    @Autowired
    private CitizenService fileBasedCitizenService;

//    @GetMapping("/search")
//    public String getSearchPage() {
//        return "search.html";
//    }
    @GetMapping("/citizens/")
    public ResponseEntity<List<Citizen>> getAllCitizens() {
        return fileBasedCitizenService.getAllCitizens().isEmpty()
                ? new ResponseEntity<>(fileBasedCitizenService.getAllCitizens(), HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(fileBasedCitizenService.getAllCitizens(), HttpStatus.OK);
    }
// /citizen?country=china
    @GetMapping("/citizens")
    public ResponseEntity<List<Citizen>> getCitizensByCountry(@RequestParam(required = false) String country) {
        List<Citizen> citizensByCountry = new ArrayList<>();
        for (Citizen citizen : fileBasedCitizenService.getAllCitizens()) {
            if (citizen.getCountry().toLowerCase().equals(country.toLowerCase())) {
                citizensByCountry.add(citizen);
            }
        }
        return citizensByCountry.isEmpty()
                ? new ResponseEntity<>(citizensByCountry, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(citizensByCountry, HttpStatus.OK);
    }


}
