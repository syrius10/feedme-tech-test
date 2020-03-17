package com.starsgroup.config;

import com.starsgroup.service.FeedProprietaryDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public FeedProprietaryDataService feedProprietaryDataService() { return new FeedProprietaryDataService(); }
}
