package com.pricetracker.dto;

import lombok.Data;

@Data
public class ResponsePayload {

	private int code;

    private int message;

    private int value;
}
