package com.starsgroup.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RawFeed {
    private final List<Event> events;
    private final List<Market> markets;
    private final List<Outcome> outcomes;

    public RawFeed() {
        events = new ArrayList<>();
        markets = new ArrayList<>();
        outcomes = new ArrayList<>();
    }

    public void clear() {
        events.clear();
        markets.clear();
        outcomes.clear();
    }

    public boolean isEmpty() {
        return events.isEmpty() && markets.isEmpty() && outcomes.isEmpty();
    }

}
