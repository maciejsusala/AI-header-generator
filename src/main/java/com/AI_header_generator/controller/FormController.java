package com.AI_header_generator.controller;

import com.AI_header_generator.model.FormData;
import com.AI_header_generator.service.ChatGPTService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class FormController {

    private final ChatGPTService chatGPTService;

    public FormController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @PostMapping("/form")
    public ResponseEntity<List<String>> generateHeaders(@RequestBody FormData formData) {
        List<String> prompts = chatGPTService.createPrompts(formData.getFormField1(), formData.getFormField2(), formData.getFormField3());
        List<String> headers = chatGPTService.generateHeaders(prompts);

        return ResponseEntity.ok(headers);
    }
}