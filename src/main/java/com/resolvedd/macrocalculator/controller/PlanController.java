package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.Plan;
import com.resolvedd.macrocalculator.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Collections;
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

    @GetMapping("/day")
    public ResponseEntity<Plan> getPlanByDay(@RequestParam("date") String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return planService.findByDate(localDate)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.ok(new Plan(localDate, Collections.emptyList(), "")));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/month")
    public ResponseEntity<List<Plan>> getPlansByMonth(@RequestParam("date") String date) {
        try {
            YearMonth yearMonth = YearMonth.parse(date);
            LocalDate startDate = yearMonth.atDay(1);
            LocalDate endDate = yearMonth.atEndOfMonth();
            return ResponseEntity.ok(planService.findByMonth(startDate, endDate));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null); // or handle the error as you see fit
        }
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
