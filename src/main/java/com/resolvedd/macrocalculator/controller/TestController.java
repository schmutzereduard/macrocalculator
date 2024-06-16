package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.Plan;
import com.resolvedd.macrocalculator.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final PlanService planService;

    @GetMapping
    public List<Plan> test() {
        return planService.findAll();
    }
}
