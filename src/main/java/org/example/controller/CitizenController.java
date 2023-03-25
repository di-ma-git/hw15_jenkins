package org.example.controller;

import org.example.model.Citizen;
import org.example.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CitizenController {

    @Autowired
    private CitizenService fileBasedCitizenService;

    @GetMapping("/citizens")
    public List<Citizen> users() {
        return fileBasedCitizenService.getAllCitizens();
    }


}
