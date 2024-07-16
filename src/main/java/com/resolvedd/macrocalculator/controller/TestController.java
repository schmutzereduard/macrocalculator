package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.Journal;
import com.resolvedd.macrocalculator.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final JournalService journalService;

    @GetMapping
    public List<Journal> test() {
        return journalService.findAll();
    }
}
