package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.Plan;
import com.resolvedd.macrocalculator.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "${cross-origin.url}")
@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping
    public ResponseEntity<List<Plan>> getPlans() {
        return ResponseEntity.ok(planService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlan(@PathVariable Long id) {
        return planService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plan> savePlan(@RequestBody Plan plan) {
        return ResponseEntity.ok(planService.save(plan));
    }

    @PutMapping
    public ResponseEntity<Plan> updatePlan(@RequestBody Plan plan) {
        return ResponseEntity.ok(planService.save(plan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
