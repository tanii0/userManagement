package com.SpringProject.userManagement.service.Impl;

import com.SpringProject.userManagement.client.GroqClient;
import com.SpringProject.userManagement.dto.AIRequest;
import com.SpringProject.userManagement.dto.AIResponse;
import com.SpringProject.userManagement.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {

    private final GroqClient groqClient;

    @Autowired
    public AIServiceImpl(GroqClient groqClient) {
        this.groqClient = groqClient;
    }

    @Override
    public AIResponse getAIResponse(AIRequest request) {
        return getAIResponse(request, "generate");
    }

    @Override
    public AIResponse getAIResponse(AIRequest request, String operationType) {
        if (request == null || request.getPrompt() == null) {
            return new AIResponse("Invalid request: prompt is missing.");
        }

        // 1. Resolve custom system prompt based on endpoint task
        String systemPrompt = getSystemPromptForOperation(operationType);

        // 2. Sanitize and structure user input (never send raw user input directly)
        String userPrompt = sanitizeAndWrapInput(request.getPrompt());

        // 3. Request completion using both system and user prompts
        String completion = groqClient.getAICompletion(systemPrompt, userPrompt);
        return new AIResponse(completion);
    }

    private String getSystemPromptForOperation(String operationType) {
        if (operationType == null) {
            return "You are a professional writing assistant.";
        }
        return switch (operationType.toLowerCase()) {
            case "generate" -> "You are a professional writing assistant. Generate clear, well-structured, and helpful content based on the user's instructions.";
            case "improve" -> "You are a professional editor. Improve the quality, clarity, structure, and flow of the text provided by the user while preserving the original core message.";
            case "rewrite" -> "You rewrite text professionally. Rewrite the user's text to make it more professional, engaging, clear, and readable.";
            case "summarize" -> "You are a professional summarization assistant. Provide a concise, clear, and accurate summary highlighting the main points of the user's text.";
            case "expand" -> "You are a detailed content writer. Expand on the user's input with additional explanations, details, and context while maintaining accuracy.";
            case "grammar" -> "You correct grammar without changing meaning. Fix spelling, punctuation, typos, and grammatical errors in the user's text. Return ONLY the corrected text without any chat or explanations.";
            case "translate" -> "You translate while preserving tone. Translate the user's text to English (or the requested target language) while keeping the original meaning, style, and tone.";
            case "continue" -> "You are a creative writer. Continue the user's text naturally, maintaining the established context, style, vocabulary, and tone.";
            case "enhance-title" -> "You generate short attractive note titles. Suggest a single, concise, professional, and catchy title based on the notes content. Return ONLY the suggested title.";
            default -> "You are a helpful writing assistant.";
        };
    }

    private String sanitizeAndWrapInput(String rawInput) {

        return "Here is the user-supplied content to process:\n\"\"\"\n" + rawInput.trim() + "\n\"\"\"";
    }
}
