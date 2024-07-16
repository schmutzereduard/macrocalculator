package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.InsulinType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "${cross-origin.url}")
@RestController
@RequestMapping("/insulin-types")
public class InsulinTypeController {

    @GetMapping
    public ResponseEntity<List<InsulinType>> getAllInsulinTypes() {
        return ResponseEntity.ok(Arrays.asList(InsulinType.values()));
    }
}
