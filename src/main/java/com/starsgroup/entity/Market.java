package com.starsgroup.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
public final class Market {
	private String eventId;
	private String marketId;
	private String name;
	private String displayed;
	private String suspended;
	private List<Outcome> outcomes;
}
