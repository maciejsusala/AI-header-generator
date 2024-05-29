package com.AI_header_generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class FormController {

    private final ChatGPTService chatGPTService;

    public FormController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @PostMapping("/form")
    public ResponseEntity<String> generateHeaders(@RequestBody FormData formData) throws JsonProcessingException {
        String prompt = chatGPTService.createPrompt(formData.getFormField1(), formData.getFormField2(), formData.getFormField3());
        String headers = chatGPTService.generateHeaders(prompt);

        ObjectMapper mapper = new ObjectMapper();
        String jsonHeaders = mapper.writeValueAsString(headers);

        return ResponseEntity.ok(jsonHeaders);
    }
}