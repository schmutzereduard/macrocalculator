package com.resolvedd.macrocalculator.controller;

import com.resolvedd.macrocalculator.model.Journal;
import com.resolvedd.macrocalculator.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;

@CrossOrigin(origins = "${cross-origin.url}")
@RestController
@RequestMapping("/journals")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;

    @GetMapping
    public ResponseEntity<List<Journal>> getJournals() {
        return ResponseEntity.ok(journalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journal> getJournal(@PathVariable Long id) {
        return journalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/day")
    public ResponseEntity<Journal> getJournalByDay(@RequestParam("date") String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return journalService.findByDay(localDate)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.ok(new Journal(localDate)));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/month")
    public ResponseEntity<List<Journal>> getJournalsByMonth(@RequestParam("date") String date) {
        try {
            YearMonth yearMonth = YearMonth.parse(date);
            LocalDate startDate = yearMonth.atDay(1);
            LocalDate endDate = yearMonth.atEndOfMonth();
            return ResponseEntity.ok(journalService.findByMonth(startDate, endDate));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null); // or handle the error as you see fit
        }
    }

    @PostMapping
    public ResponseEntity<Journal> saveJournal(@RequestBody Journal journal) {
        return ResponseEntity.ok(journalService.save(journal));
    }

    @PutMapping
    public ResponseEntity<Journal> updateJournal(@RequestBody Journal journal) {
        return ResponseEntity.ok(journalService.save(journal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable Long id) {
        journalService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}