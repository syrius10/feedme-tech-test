package com.starsgroup.controller;

import com.starsgroup.entity.Event;
import com.starsgroup.service.FeedProprietaryDataService;
import com.starsgroup.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
public class RestAppController {

    @Autowired
    private FeedProprietaryDataService feedProprietaryDataService;

    @Autowired
    private FixtureService fixtureService;

    @RequestMapping("/processFeeds")
    public ResponseEntity receiveAndTransformData() throws IOException {
        feedProprietaryDataService.receiveData();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/fixtures", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getFixtures() {
        List<Event> events = fixtureService.getAllEvents();
        return new ResponseEntity(events, HttpStatus.OK);
    }

    @RequestMapping(path = "/abortFeedsProcess", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity abortFeedsProcess() throws IOException {
        List<Event> persistedEvents = feedProprietaryDataService.abortFeedProcess();
        return new ResponseEntity(persistedEvents, HttpStatus.OK);
    }
}
