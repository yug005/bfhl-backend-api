package com.yug.bfhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BfhlApplication {

    public static void main(String[] args) {
        SpringApplication.run(BfhlApplication.class, args);
    }

    // just for testing api key
    // @Bean
    // CommandLineRunner testGemini(GeminiService geminiService) {
    // return args -> {
    // String answer = geminiService.getOneWordAnswer("What is the capital of
    // Maharashtra?");
    // System.out.println(">>> Gemini Answer: " + answer);
    // };
    // }

}
