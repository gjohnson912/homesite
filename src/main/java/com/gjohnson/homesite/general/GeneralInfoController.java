package com.gjohnson.homesite.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/general")
public class GeneralInfoController {

    private final GeneralInfoService generalInfoService;

    @Autowired
    public GeneralInfoController(GeneralInfoService generalInfoService) {
        this.generalInfoService = generalInfoService;
    }

    @GetMapping(path = "/default")
    public HttpEntity<GeneralInfo> getSystemDefaultGeneralInfo() {
        return ResponseEntity.of(this.generalInfoService.retrieveSystemDefaultGeneralInfo());
    }
}
