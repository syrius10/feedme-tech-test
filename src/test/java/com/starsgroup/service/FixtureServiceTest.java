package com.starsgroup.service;

import com.starsgroup.entity.Event;
import com.starsgroup.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class FixtureServiceTest {

    @Mock
    private EventRepository eventRepositoryMock;

    private FixtureService fixtureService;

    @Before
    public void setUp() {
        initMocks(this);
        fixtureService = new FixtureService();
        fixtureService.setEventRepository(eventRepositoryMock);
    }

    @Test
    public void shouldAddEvents() {
        List<Event> events = Arrays.asList(Event.builder().build());
        fixtureService.addEvents(events);
        verify(eventRepositoryMock).save(Matchers.anyListOf(Event.class));
    }

    @Test
    public void shouldUpdateEvent() {
        fixtureService.updateEvent(Event.builder().build());
        verify(eventRepositoryMock).save(Matchers.any(Event.class));
    }

    @Test
    public void shouldSearchAndReturnAllEvents() {
        List<Event> events = Arrays.asList(Event.builder().build());
        when(eventRepositoryMock.findAll()).thenReturn(events);
        fixtureService.getAllEvents();
        verify(eventRepositoryMock).findAll();
    }

}