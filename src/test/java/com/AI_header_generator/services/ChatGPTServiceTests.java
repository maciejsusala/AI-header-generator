package com.AI_header_generator.services;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ChatGPTServiceTests {

    @InjectMocks
    private ChatGPTService chatGPTService;

    @Mock
    private OpenAiService openAiService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePrompts() {
        List<String> prompts = chatGPTService.createPrompts("cel", "obiekcja", "produkt");
        assertEquals(3, prompts.size());
        for (String prompt : prompts) {
            assertEquals("Stwórz propozycję nagłówka, który przyciągnie uwagę odbiorcy, obiecując pokonanie OBIEKCJI i osiągnięcie CELU za pomocą PRODUKTU. Nagłówek powinien mieć nie więcej niż 15 wyrazów. CEL = cel OBIEKCJA = obiekcja PRODUKT = produkt", prompt);
        }
    }

    @Test
    public void testGenerateHeaders() {
        ChatCompletionChoice choice = new ChatCompletionChoice();
        choice.setMessage(new ChatMessage("model", "header"));
        ChatCompletionResult result = new ChatCompletionResult();
        result.setChoices(List.of(choice));
        when(openAiService.createChatCompletion(any(ChatCompletionRequest.class))).thenReturn(result);

        List<String> headers = chatGPTService.generateHeaders(Arrays.asList("prompt1", "prompt2", "prompt3"));
        assertEquals(3, headers.size());
        for (String header : headers) {
            assertNotNull(header);
            assertFalse(header.contains("Error:"));
        }
    }
}