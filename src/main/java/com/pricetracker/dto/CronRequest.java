package com.pricetracker.dto;


import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class CronRequest {
	
	@Autowired
	private String url;

    private boolean sendNotification;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isSendNotification() {
		return sendNotification;
	}

	public void setSendNotification(boolean sendNotification) {
		this.sendNotification = sendNotification;
	}

}
