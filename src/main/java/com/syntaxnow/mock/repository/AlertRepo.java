package com.syntaxnow.mock.repository;

import com.syntaxnow.mock.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepo extends JpaRepository<Alert, String> {

    // For: GET /alerts?severity=Critical
    List<Alert> findBySeverityIgnoreCase(String severity);
}
