package com.altruithm.backend.controller;
import com.altruithm.backend.model.FraudRiskResponse;
import com.altruithm.backend.model.FraudDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows access from any frontend for development

public class AltruithmController {
    @Autowired
    private FraudDetectionService fraudService;

    @PostMapping("/fraud/check")
    public ResponseEntity<FraudRiskResponse> checkFraud(@RequestBody Map<String, Object> request) {
        String charityName = (String) request.get("charityName");
        Double amount = request.get("amount") != null ? ((Number) request.get("amount")).doubleValue() : 0.0;
        FraudRiskResponse response = fraudService.analyzeFraudRisk(charityName, amount);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/test")
    public String test() {
        return "Altruithm Fraud Detection API is running!";
    }

    @GetMapping("/get_similar_charities")
    public ResponseEntity<Map<String, String>> getSimilarCharities(@RequestParam String name) {
        return ResponseEntity.ok(Map.of(
                "message", "Received request for similar charities to: " + name
        ));
    }

    @GetMapping("/get_charities_by_interests")
    public ResponseEntity<Map<String, String>> getCharitiesByInterests(@RequestParam String interests) {
        return ResponseEntity.ok(Map.of(
                "message", "Received request for charities by interests: " + interests
        ));
    }
}