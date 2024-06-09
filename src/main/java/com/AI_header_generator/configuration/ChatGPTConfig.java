package com.AI_header_generator.configuration;


import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatGPTConfig {

    @Value("${OPENAI_KEY}")
    private String openAiKey;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(openAiKey);
    }
}
