package com.pricetracker.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.Data;

import javax.persistence.GenerationType;


@Data
@Entity
@Table(name = "requests")
@Repository
public class ScrappingRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

    private String emailId;
    
    @Column(name = "send_notification", columnDefinition = "boolean default false")
    private boolean sendNotification;
    
    @Column(name = "price", columnDefinition = "Decimal(10,2) default '0.00'")
    private long price;
    
    @Lob
    private String url;

	public boolean isSendNotification() {
		return sendNotification;
	}

	public void setSendNotification(boolean sendNotification) {
		this.sendNotification = sendNotification;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}    
    
}
