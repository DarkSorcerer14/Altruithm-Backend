package com.altruithm.backend.controller;

import com.altruithm.backend.Entity.CharityBasic;
import com.altruithm.backend.model.FraudRiskResponse;
import com.altruithm.backend.model.FraudDetectionService;
import com.altruithm.backend.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AltruithmController {

    @Autowired
    private FraudDetectionService fraudService;

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/fraud/check")
    public ResponseEntity<FraudRiskResponse> checkFraud(@RequestBody Map<String, Object> request) {
        String charityName = (String) request.get("charityName");
        Double amount = request.get("amount") != null ? ((Number) request.get("amount")).doubleValue() : 0.0;
        FraudRiskResponse response = fraudService.analyzeFraudRisk(charityName, amount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String test() {
        return "Altruithm API is running!";
    }

    @GetMapping("/get_similar_charities")
    public ResponseEntity<List<CharityBasic>> getSimilarCharities(@RequestParam String name) {
        List<CharityBasic> charities = recommendationService.getSimilarCharities(name);
        return ResponseEntity.ok(charities);
    }

    @GetMapping("/get_charities_by_interests")
    public ResponseEntity<List<CharityBasic>> getCharitiesByInterests(@RequestParam String interests) {
        List<CharityBasic> charities = recommendationService.getCharitiesByInterests(interests);
        return ResponseEntity.ok(charities);
    }
}