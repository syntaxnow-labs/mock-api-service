package com.syntaxnow.mock.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntaxnow.mock.model.Alert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class AlertRepository {

    private final List<Alert> alerts = new CopyOnWriteArrayList<>();
    private final Random random = new Random();

    private static final List<String> SEVERITIES = List.of("Critical", "Warning", "Info");
    private static final List<String> PROJECTS = List.of(
            "Simulation Proj 1", "Fleet Automation", "Edge Analytics", "Smart City Grid",
            "Retail Connect", "Energy Insights", "Warehouse Sensors", "AgroSense",
            "AeroTrack", "TransitIQ"
    );
    private static final List<String> MESSAGES = List.of(
            "Device disconnected for >30 minutes",
            "High packet loss detected",
            "Firmware update failed",
            "Overheating threshold exceeded",
            "Unexpected reboot event",
            "SIM inactive or missing",
            "Network latency spike",
            "Data usage exceeded 90%",
            "Sensor data anomaly detected"
    );

    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/data/alerts.json")) {
            Map<String, Object> wrapper = mapper.readValue(is, Map.class);
            List<Map<String, Object>> data = (List<Map<String, Object>>) wrapper.get("alerts");
            data.forEach(d -> alerts.add(mapper.convertValue(d, Alert.class)));
        }
    }

    public List<Alert> findAll(String severity) {
        if (severity == null || severity.isBlank()) {
            return alerts;
        }
        return alerts.stream()
                .filter(a -> a.getSeverity().equalsIgnoreCase(severity))
                .toList();
    }

    public Optional<Alert> findById(String id) {
        return alerts.stream().filter(a -> a.getId().equalsIgnoreCase(id)).findFirst();
    }

    public void save(Alert alert) {
        alerts.add(alert);
    }

    public void updateAck(String id, boolean acknowledged) {
        findById(id).ifPresent(a -> a.setAcknowledged(acknowledged));
    }

    public Alert simulateNewAlert() {
        Alert alert = new Alert();
        alert.setId("ALT" + (alerts.size() + 1));
        alert.setProjectName(PROJECTS.get(random.nextInt(PROJECTS.size())));
        alert.setDeviceId(String.valueOf(900000000100000L + random.nextInt(899999)));
        alert.setSeverity(SEVERITIES.get(random.nextInt(SEVERITIES.size())));
        alert.setMessage(MESSAGES.get(random.nextInt(MESSAGES.size())));
        alert.setTimestamp(Instant.now().toString());
        alert.setAcknowledged(false);
        alerts.add(0, alert); // add to top for recency
        return alert;
    }
}
