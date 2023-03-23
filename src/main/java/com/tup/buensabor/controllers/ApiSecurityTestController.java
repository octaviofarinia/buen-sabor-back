package com.tup.buensabor.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiSecurityTestController {

    @GetMapping(value = "/public")
    public ResponseEntity<?> publicEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body("All good. You DO NOT need to be authenticated to call /api/public.");
    }

    @GetMapping(value = "/private")
    public ResponseEntity<?> privateEndpoint() {
        return ResponseEntity.status(HttpStatus.OK).body("All good. You can see this because you are Authenticated.");
    }

}
