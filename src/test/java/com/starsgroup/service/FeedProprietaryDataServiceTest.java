package com.starsgroup.service;

import com.starsgroup.entity.Event;
import com.starsgroup.entity.Market;
import com.starsgroup.entity.Outcome;
import com.starsgroup.entity.RawFeed;
import com.starsgroup.util.FeedType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FeedProprietaryDataServiceTest {

    private FeedProprietaryDataService feedProprietaryDataService;

    @Mock
    Socket socketMocked;

    @Mock
    InputStream inputStreamMocked;

    @Mock
    RawFeed rawFeedMocked;

    @Mock
    BufferedReader bufferedReaderMocked;

    @Mock
    FixtureService fixtureServiceMocked;

    @Before
    public void setUp() throws IOException {
        feedProprietaryDataService = new FeedProprietaryDataService();
        feedProprietaryDataService.setClientSocket(socketMocked);
        when(socketMocked.getInputStream()).thenReturn(inputStreamMocked);
    }

    @Test
    public void abortFeedProcess() throws IOException {
        RawFeed rawFeed = createRawFeed();
        feedProprietaryDataService.setFixtureService(fixtureServiceMocked);
        doNothing().when(fixtureServiceMocked).addEvents(anyListOf(Event.class));
        when(fixtureServiceMocked.getAllEvents()).thenReturn(rawFeed.getEvents());
        feedProprietaryDataService.setRawFeed(rawFeed);
        feedProprietaryDataService.abortFeedProcess();
    }

    @Test
    public void parseFeed() {
        String inputLine = "|2054|create|event|1497359166352|ee4d2439-e1c5-4cb7-98ad-9879b2fd84c2|Football|Sky Bet League Two|\\|Accrington\\| vs \\|Cambridge\\||1497359216693|0|1|";
        RawFeed rawFeed = new RawFeed();
        Event expectedEvent = Event.builder()
                .eventId("ee4d2439-e1c5-4cb7-98ad-9879b2fd84c2")
                .category("Football")
                .subCategory("Sky Bet League Two")
                .name("\\|Accrington\\| vs \\|Cambridge\\|")
                .startTime("1497359216693")
                .displayed("0")
                .suspended("1")
                .build();

        feedProprietaryDataService.setRawFeed(rawFeed);
        feedProprietaryDataService.parseFeed(inputLine, FeedType.event.name());

        assertEquals(expectedEvent, rawFeed.getEvents().get(0));
    }

    @Test
    public void transformFeeds() {
        RawFeed rawFeed = createRawFeed();
        feedProprietaryDataService.setRawFeed(rawFeed);
        assertNull(rawFeed.getEvents().get(0).getMarkets());
        assertNull(rawFeed.getMarkets().get(0).getOutcomes());

        List<Event> transformed = feedProprietaryDataService.transformFeeds();

        Event expectedEvent = rawFeed.getEvents().get(0);
        Event actualEvent = transformed.get(0);
        assertEquals(expectedEvent, actualEvent);

        Market expectedMarket = rawFeed.getEvents().get(0).getMarkets().get(0);
        Market actualMarket = transformed.get(0).getMarkets().get(0);
        assertEquals(expectedMarket, actualMarket);

        Outcome expectedOutcome = rawFeed.getOutcomes().get(0);
        Outcome actualOutcome = transformed.get(0).getMarkets().get(0).getOutcomes().get(0);
        assertEquals(expectedOutcome, actualOutcome);
    }

    @Test
    public void dbUpsert() {
        String inputLine = "|2054|create|event|1497359166352|ee4d2439-e1c5-4cb7-98ad-9879b2fd84c2|Football|Sky Bet League Two|\\|Accrington\\| vs \\|Cambridge\\||1497359216693|0|1|";

        feedProprietaryDataService.setRawFeed(rawFeedMocked);
        feedProprietaryDataService.dbUpsert(inputLine);
        verify(rawFeedMocked).getEvents();
    }

    private RawFeed createRawFeed() {
        Event event = Event.builder()
                .eventId("fff76bb6-59ab-41ff-b5a7-5e14fec38a69")
                .category("Football")
                .subCategory("Sky Bet League Two")
                .name("\\|Port Vale\\| vs \\|Carlisle\\|")
                .startTime("1575378818834")
                .displayed("0")
                .suspended("1")
                .build();

        Market market = Market.builder()
                .eventId("fff76bb6-59ab-41ff-b5a7-5e14fec38a69")
                .marketId("193ab64f-279f-4a7d-9531-007199442320")
                .name("Full Time Result")
                .displayed("0")
                .suspended("1")
                .build();

        Outcome outcome = Outcome.builder()
                .marketId("193ab64f-279f-4a7d-9531-007199442320")
                .outcomeId("0dde5103-e7f6-412c-98d6-f4e83cfddfac")
                .name("\\|Port Vale\\|")
                .price("1/5")
                .displayed("0")
                .suspended("1")
                .build();

        RawFeed rawFeed = new RawFeed();
        rawFeed.getEvents().add(event);
        rawFeed.getMarkets().add(market);
        rawFeed.getOutcomes().add(outcome);

        return rawFeed;
    }

}