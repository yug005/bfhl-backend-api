package com.yug.bfhl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private static final String FALLBACK = "unknown";

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GeminiService() {
        this.restTemplate = new RestTemplate();
    }

    public String getOneWordAnswer(String question) {
        try {
            String url = apiUrl + "?key=" + apiKey;

            Map<String, Object> part = Map.of("text", "Answer in exactly one word: " + question);
            Map<String, Object> content = Map.of("parts", List.of(part));
            Map<String, Object> body = Map.of("contents", List.of(content));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

            return extractWord(response.getBody());
        } catch (Exception e) {
            return FALLBACK;
        }
    }

    @SuppressWarnings("unchecked")
    private String extractWord(Map<String, Object> responseBody) {
        if (responseBody == null)
            return FALLBACK;

        List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
        if (candidates == null || candidates.isEmpty())
            return FALLBACK;

        Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
        if (content == null)
            return FALLBACK;

        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
        if (parts == null || parts.isEmpty())
            return FALLBACK;

        String text = (String) parts.get(0).get("text");
        if (text == null || text.trim().isEmpty())
            return FALLBACK;

        return text.trim().split("\\s+")[0].replaceAll("[^a-zA-Z0-9]", "");
    }

}
