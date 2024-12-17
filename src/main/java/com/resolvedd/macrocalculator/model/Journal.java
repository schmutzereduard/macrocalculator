package com.resolvedd.macrocalculator.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class Journal {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long profileId;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "journal", cascade = ALL, orphanRemoval = true)
    private List<JournalEntry> entries = new ArrayList<>();

    public Journal(LocalDate date) {
        this.date = date;
    }

    public void addEntry(JournalEntry entry) {
        entries.add(entry);
        entry.setJournal(this);
    }

    public void removeEntry(JournalEntry entry) {
        entries.remove(entry);
        entry.setJournal(null);
    }
}
