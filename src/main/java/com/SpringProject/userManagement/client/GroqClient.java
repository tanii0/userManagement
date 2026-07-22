package com.SpringProject.userManagement.client;

import com.SpringProject.userManagement.exception.AIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class GroqClient {

    private final WebClient webClient;
    private final String apiKey;
    private final String model;
    private final String apiUrl;

    @Autowired
    public GroqClient(
            WebClient.Builder webClientBuilder,
            @Value("${groq.api.key}") String apiKey,
            @Value("${groq.model}") String model,
            @Value("${groq.api.url:https://api.groq.com/openai/v1/chat/completions}") String apiUrl) {
        this.webClient = webClientBuilder.build();
        this.apiKey = apiKey;
        this.model = model;
        this.apiUrl = apiUrl;
    }

    /**
     * Sends a chat completion prompt request to the external Groq API using Spring WebClient
     * and returns the generated textual response.
     */
    public String getAICompletion(String prompt) {
        return getAICompletion("You are a helpful writing assistant.", prompt);
    }

    /**
     * Overloaded method to support structured system instructions and user content separation.
     */
    public String getAICompletion(String systemPrompt, String userPrompt) {
        try {
            // Build request with both system instructions and user prompt
            GroqRequest requestPayload = new GroqRequest(
                    this.model,
                    List.of(
                            new GroqRequest.Message("system", systemPrompt),
                            new GroqRequest.Message("user", userPrompt)
                    )
            );

            // Execute POST request to Groq API
            GroqResponse response = this.webClient.post()
                    .uri(this.apiUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.apiKey)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(requestPayload)
                    .retrieve()
                    .bodyToMono(GroqResponse.class)
                    .onErrorMap(e -> new AIException("Failed to request chat completion from Groq API: " + e.getMessage(), e))
                    .block();

            // Extract completion content from response choices
            if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                return response.getChoices().get(0).getMessage().getContent();
            } else {
                throw new AIException("Groq API returned an empty or invalid response.");
            }
        } catch (Exception e) {
            if (e instanceof AIException) {
                throw (AIException) e;
            }
            throw new AIException("Error processing request inside GroqClient: " + e.getMessage(), e);
        }
    }

    // Inner DTO class representing the chat completion request payload
    public static class GroqRequest {
        private String model;
        private List<Message> messages;

        public GroqRequest() {}

        public GroqRequest(String model, List<Message> messages) {
            this.model = model;
            this.messages = messages;
        }

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public List<Message> getMessages() { return messages; }
        public void setMessages(List<Message> messages) { this.messages = messages; }

        public static class Message {
            private String role;
            private String content;

            public Message() {}

            public Message(String role, String content) {
                this.role = role;
                this.content = content;
            }

            public String getRole() { return role; }
            public void setRole(String role) { this.role = role; }
            public String getContent() { return content; }
            public void setContent(String content) { this.content = content; }
        }
    }

    // Inner DTO class representing the chat completion response payload
    public static class GroqResponse {
        private List<Choice> choices;

        public List<Choice> getChoices() { return choices; }
        public void setChoices(List<Choice> choices) { this.choices = choices; }

        public static class Choice {
            private Message message;

            public Message getMessage() { return message; }
            public void setMessage(Message message) { this.message = message; }
        }

        public static class Message {
            private String content;

            public String getContent() { return content; }
            public void setContent(String content) { this.content = content; }
        }
    }
}
