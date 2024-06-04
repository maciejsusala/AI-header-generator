package com.AI_header_generator.services;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGPTService {

    private final OpenAiService openAiService;

    public ChatGPTService(@Value("${OPENAI_KEY}") String openAiKey) {
        this.openAiService = new OpenAiService(openAiKey);
    }


    public List<String> createPrompts(String formField1, String formField2, String formField3) {
        String promptPart1 = "Stwórz propozycję nagłówka, który przyciągnie uwagę odbiorcy, obiecując pokonanie OBIEKCJI i osiągnięcie CELU za pomocą PRODUKTU. Nagłówek powinien mieć nie więcej niż 15 wyrazów. CEL = ";
        String promptPart2 = " OBIEKCJA = ";
        String promptPart3 = " PRODUKT = ";
        List<String> prompts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            prompts.add(promptPart1 + formField1 + promptPart2 + formField2 + promptPart3 + formField3);
        }
        return prompts;
    }

    public List<String> generateHeaders(List<String> prompts) {
        List<String> headers = new ArrayList<>();
        for (String prompt : prompts) {
            ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                    .messages(List.of(new ChatMessage("user", prompt)))
                    .model("gpt-3.5-turbo")
                    .maxTokens(100)
                    .temperature(0.7)
                    .build();
            List<ChatCompletionChoice> choices = openAiService.createChatCompletion(completionRequest).getChoices();
            headers.add(choices.get(0).getMessage().getContent());
        }
        return headers;
    }
}