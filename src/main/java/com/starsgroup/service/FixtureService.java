package com.starsgroup.service;

import com.starsgroup.entity.Event;
import com.starsgroup.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FixtureService {

	private EventRepository eventRepository;

    public FixtureService() {}

    public void addEvents(List<Event> events) {
    	eventRepository.save(events);
    }

    public void updateEvent(Event event) { eventRepository.save(event); }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

}
