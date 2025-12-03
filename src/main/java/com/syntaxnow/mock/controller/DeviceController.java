package com.syntaxnow.mock.controller;

import com.syntaxnow.mock.model.Device;
import com.syntaxnow.mock.model.DeviceResponse;
import com.syntaxnow.mock.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/devices")
@AllArgsConstructor
public class DeviceController {

    private final DeviceRepository repository;

    @GetMapping
    public DeviceResponse getAll() {
        List<Device> data = repository.findAll();
        return new DeviceResponse(
                data,
                data.size(),
                "static-device-key",
                Instant.now().toEpochMilli()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> get(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Device> create(@RequestBody Device device) {
        repository.save(device);
        return ResponseEntity.ok(device);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> update(@PathVariable String id, @RequestBody Device updated) {
        updated.setId(id);
        repository.update(updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        repository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
