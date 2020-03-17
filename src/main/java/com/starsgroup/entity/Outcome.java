package com.starsgroup.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public final class Outcome {
	private String marketId;
	private String outcomeId;
	private String name;
	private String price;
	private String displayed;
	private String suspended;
}
