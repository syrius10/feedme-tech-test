package com.starsgroup.config;

import com.starsgroup.service.FeedProprietaryDataService;
import com.starsgroup.service.FixtureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public FixtureService fixtureService() { return new FixtureService(); }

    @Bean
    public FeedProprietaryDataService feedProprietaryDataService() { return new FeedProprietaryDataService(); }
}
