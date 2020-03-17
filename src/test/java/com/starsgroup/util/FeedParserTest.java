package com.starsgroup.util;

import com.starsgroup.entity.Event;
import com.starsgroup.entity.FeedHeader;
import com.starsgroup.entity.Market;
import com.starsgroup.entity.Outcome;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FeedParserTest {

    @Test
    public void parseHeader() {
        String inputLine = "|2054|create|event|1497359166352|ee4d2439-e1c5-4cb7-98ad-9879b2fd84c2|Football|Sky Bet League Two|\\|Accrington\\| vs \\|Cambridge\\||1497359216693|0|1|";
        FeedHeader feedHeader = FeedParser.parseHeader(inputLine);
        assertEquals("2054", feedHeader.getMsgId());
        assertEquals("create", feedHeader.getOperation());
        assertEquals("event", feedHeader.getType());
        assertEquals("1497359166352", feedHeader.getTimestamp());
    }

    @Test
    public void parseEvent() {
        String inputLine = "|1|create|event|1575308756429|ffd3f0d1-3a6a-489f-8704-7d913930df84|Tennis|US Open|\\|Novak Dojokvic\\| vs \\|Roger Federer\\||1575308786056|0|1|";
        Event event = FeedParser.parseEvent(inputLine);
        assertEquals("ffd3f0d1-3a6a-489f-8704-7d913930df84", event.getEventId());
        assertEquals("Tennis", event.getCategory());
        assertEquals("US Open", event.getSubCategory());
        assertEquals("\\|Novak Dojokvic\\| vs \\|Roger Federer\\|", event.getName());
        assertEquals("0", event.getDisplayed());
        assertEquals("1", event.getSuspended());
    }

    @Test
    public void parseMarket() {
        String inputLine = "|108|update|market|1575308760853|ff0a97f0-fea9-4a19-9f34-bca519f6bd8d|1cb37ebb-baab-45d8-809a-b11354592ea8|Half Time Result|1|0|";
        Market market = FeedParser.parseMarket(inputLine);
        assertEquals("ff0a97f0-fea9-4a19-9f34-bca519f6bd8d", market.getEventId());
        assertEquals("1cb37ebb-baab-45d8-809a-b11354592ea8", market.getMarketId());
        assertEquals("Half Time Result", market.getName());
        assertEquals("1", market.getDisplayed());
        assertEquals("0", market.getSuspended());
    }

    @Test
    public void parseOutcome() {
        String inputLine = "|14|create|outcome|1575308756433|0e637fe3-53b8-44d0-8bc5-dfe24f01aeb7|c2913765-bb42-475e-b4ff-e65195a6c36c|\\|Roger Federer\\| to win a set|5/4|0|1|";
        Outcome outcome = FeedParser.parseOutcome(inputLine);
        assertEquals("0e637fe3-53b8-44d0-8bc5-dfe24f01aeb7", outcome.getMarketId());
        assertEquals("c2913765-bb42-475e-b4ff-e65195a6c36c", outcome.getOutcomeId());
        assertEquals("\\|Roger Federer\\| to win a set", outcome.getName());
        assertEquals("5/4", outcome.getPrice());
        assertEquals("0", outcome.getDisplayed());
        assertEquals("1", outcome.getSuspended());
    }
}