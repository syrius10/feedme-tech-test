package com.starsgroup.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public final class FeedHeader {
    private String msgId;
	private String operation;
	private String type;
    private String timestamp;
}
