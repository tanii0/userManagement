package com.SpringProject.userManagement.controller;

import com.SpringProject.userManagement.dto.AIRequest;
import com.SpringProject.userManagement.dto.AIResponse;
import com.SpringProject.userManagement.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    @Autowired
    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    public AIResponse generate(@RequestBody AIRequest request) {
        return aiService.getAIResponse(request, "generate");
    }

    @PostMapping("/improve")
    public AIResponse improve(@RequestBody AIRequest request) {
        return aiService.getAIResponse(request, "improve");
    }

    @PostMapping("/rewrite")
    public AIResponse rewrite(@RequestBody AIRequest request) {
        return aiService.getAIResponse(request, "rewrite");
    }

    @PostMapping("/summarize")
    public AIResponse summarize(@RequestBody AIRequest request) {
        return aiService.getAIResponse(request, "summarize");
    }

    @PostMapping("/expand")
    public AIResponse expand(@RequestBody AIRequest request) {
        return aiService.getAIResponse(request, "expand");
    }

    @PostMapping("/grammar")
    public AIResponse grammar(@RequestBody AIRequest request) {
        return aiService.getAIResponse(request, "grammar");
    }

    @PostMapping("/translate")
    public AIResponse translate(@RequestBody AIRequest request) {
        return aiService.getAIResponse(request, "translate");
    }

    @PostMapping("/continue")
    public AIResponse continueText(@RequestBody AIRequest request) {
        return aiService.getAIResponse(request, "continue");
    }

    @PostMapping("/enhance-title")
    public AIResponse enhanceTitle(@RequestBody AIRequest request) {
        return aiService.getAIResponse(request, "enhance-title");
    }
}
