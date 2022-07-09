package com.borymskyi.trail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping
    public ResponseEntity main() {
        try {
            return ResponseEntity.ok("oAu2");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error");
        }
    }
}
