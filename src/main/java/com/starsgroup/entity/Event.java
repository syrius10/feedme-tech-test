package com.starsgroup.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@EqualsAndHashCode
@Document(collection = "event")
public final class Event {
	@Id
    private String eventId;
	private String category;
	private String subCategory;
    private String name;
    private String startTime;
	private String displayed;
	private String suspended;
	private List<Market> markets;
}
