package com.starsgroup.util;

import com.starsgroup.entity.Event;
import com.starsgroup.entity.FeedHeader;
import com.starsgroup.entity.Market;
import com.starsgroup.entity.Outcome;

public class FeedParser {

    public static FeedHeader parseHeader (String feedString) {
        String[] feed = tokenise(feedString);
        return FeedHeader.builder()
                .msgId(feed[1])
                .operation(feed[2])
                .type(feed[3])
                .timestamp(feed[4])
                .build();
    }

    public static Event parseEvent (String feedString) {
        String[] feed = tokenise(feedString);
        return Event.builder()
                .eventId(feed[5])
                .category(feed[6])
                .subCategory(feed[7])
                .name(feed[8])
                .startTime(feed[9])
                .displayed(feed[10])
                .suspended(feed[11])
                .build();
    }

    public static Market parseMarket(String feedString) {
        String[] feed = tokenise(feedString);
        return Market.builder()
                .eventId(feed[5])
                .marketId(feed[6])
                .name(feed[7])
                .displayed(feed[8])
                .suspended(feed[9])
                .build();
    }

    public static Outcome parseOutcome(String feedString) {
        String[] feed = tokenise(feedString);
        return Outcome.builder()
                .marketId(feed[5])
                .outcomeId(feed[6])
                .name(feed[7])
                .price(feed[8])
                .displayed(feed[9])
                .suspended(feed[10])
                .build();
    }

    private static String[] tokenise(String feedString) {
        return feedString.split("(?<!\\\\)(\\|)");
    }

}
