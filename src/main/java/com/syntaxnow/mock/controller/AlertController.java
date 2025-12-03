package com.syntaxnow.mock.controller;

import com.syntaxnow.mock.model.Alert;
import com.syntaxnow.mock.repository.AlertRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alerts")
@AllArgsConstructor
public class AlertController {

    private final AlertRepository repository;

    // GET /api/alerts?severity=Critical
    @GetMapping
    public List<Alert> getAllAlerts(@RequestParam(required = false) String severity) {
        return repository.findAll(severity);
    }

    @GetMapping("/summary")
    public Map<String, Long> getSeveritySummary() {
        return repository.findAll(null).stream()
                .collect(Collectors.groupingBy(Alert::getSeverity, Collectors.counting()));
    }

    // PATCH /api/alerts/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Alert> updateAck(@PathVariable String id, @RequestBody Map<String, Boolean> body) {
        boolean acknowledged = body.getOrDefault("acknowledged", true);
        repository.updateAck(id, acknowledged);
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * That will inject a fresh random alert every 10 seconds — just like a real IoT monitoring stream.
     * setInterval(async () => {
     *   await fetch("/api/alerts/simulate", { method: "POST" });
     * }, 10000);
     *
     */
    // POST /api/alerts/simulate
    @PostMapping("/simulate")
    public Alert injectNewAlert() {
        return repository.simulateNewAlert();
    }
}
