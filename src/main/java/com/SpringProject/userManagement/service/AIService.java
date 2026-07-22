package com.SpringProject.userManagement.service;

import com.SpringProject.userManagement.dto.AIRequest;
import com.SpringProject.userManagement.dto.AIResponse;

public interface AIService {
    AIResponse getAIResponse(AIRequest request);
    AIResponse getAIResponse(AIRequest request, String operationType);
}
