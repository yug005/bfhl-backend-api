package com.yug.bfhl.controller;

import com.yug.bfhl.model.ApiResponse;
import com.yug.bfhl.service.GeminiService;
import com.yug.bfhl.util.MathUtils;
import com.yug.bfhl.util.RequestValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BfhlController {

    private final GeminiService geminiService;

    public BfhlController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<ApiResponse> handlePost(@RequestBody Map<String, Object> body) {
        RequestValidator.validate(body);

        String key = body.keySet().iterator().next();
        Object value = body.get(key);

        Object result;

        switch (key) {
            case "fibonacci":
                int n = ((Number) ((List<?>) value).get(0)).intValue();
                result = MathUtils.fibonacci(n);
                break;
            case "prime":
                result = MathUtils.filterPrimes(toIntList(value));
                break;
            case "lcm":
                result = MathUtils.lcm(toIntList(value));
                break;
            case "hcf":
                result = MathUtils.hcf(toIntList(value));
                break;
            case "AI":
                result = geminiService.getOneWordAnswer((String) value);
                break;
            default:
                result = null;
        }

        ApiResponse response = new ApiResponse();
        response.setIs_success(true);
        response.setData(result);
        return ResponseEntity.ok(response);
    }

    @SuppressWarnings("unchecked")
    private List<Integer> toIntList(Object value) {
        return ((List<Object>) value).stream()
                .map(item -> ((Number) item).intValue())
                .collect(Collectors.toList());
    }

}
