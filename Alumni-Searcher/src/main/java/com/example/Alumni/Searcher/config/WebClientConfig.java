package com.example.Alumni.Searcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient phantomClient() {
        return WebClient.builder()
                .baseUrl("https://api.phantombuster.com")
                //  PHANTOMBUSTER_API_KEY=your_api_key_here
                .defaultHeader("X-Phantombuster-Key-1", System.getenv("PHANTOMBUSTER_API_KEY"))
                .build();
    }
}
