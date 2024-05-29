package com.AI_header_generator;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatGPTService {

    private final OpenAiService openAiService;

//    public ChatGPTService(@Value("${OPENAI_KEY}") String openAiKey) {
//        this.openAiService = new OpenAiService(openAiKey);
//    }

    public ChatGPTService() {
        String openAiKey = "sk-proj-eoxCjKAuVX5LifHx9F8UT3BlbkFJn332TekXYmodgOgNTKK4";
        this.openAiService = new OpenAiService(openAiKey);
    }

    public String createPrompt(String formField1, String formField2, String formField3) {
        String promptPart1 = "Stwórz 5 propozycji nagłówka, który przyciągnie uwagę odbiorcy, obiecując pokonanie OBIEKCJI i osiągnięcie CELU za pomocą PRODUKTU. Nagłówek powinien mieć nie więcej niż 15 wyrazów. CEL = ";
        String promptPart2 = " OBIEKCJA = ";
        String promptPart3 = " PRODUKT = ";
        String promptPart4 = "Propozycje rozdziel znakiem nowej linii ";
        return promptPart1 + formField1 + promptPart2 + formField2 + promptPart3 + formField3 + promptPart4;
    }

    public String generateHeaders(String prompt) {

        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(List.of(new ChatMessage("user", prompt)))
                .model("gpt-3.5-turbo")
                .maxTokens(100)
                .temperature(0.7)
                .build();
        List<ChatCompletionChoice> choices = openAiService.createChatCompletion(completionRequest).getChoices();

        StringBuilder stringBuilder = new StringBuilder();

        choices.stream()
                .map(ChatCompletionChoice::getMessage)
                .map(ChatMessage::getContent)
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }
}