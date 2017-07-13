package com.pricetracker.action;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pricetracker.dto.ScrappingRequest;
import com.pricetracker.notificationClient.NotificationClient;
import com.pricetracker.parser.WebScrapper;
import com.pricetracker.repositories.RequestRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UpdatePriceByBulkAction implements Action{
	
	public static Logger LOG = Logger.getLogger(UpdatePriceByBulkAction.class);
	
	@Autowired
    private RequestRepository requestRepository;

    @Autowired
    private WebScrapper webScrapper;

    @Autowired
    private NotificationClient notificationClient;

	@Override
	public Object invoke() {
		 Long start = System.currentTimeMillis();
	        List<ScrappingRequest> scrappingRequestList = requestRepository.findBySendNotification(false);
	        LOG.info("Query Result " + scrappingRequestList.size());
	        for (ScrappingRequest scrappingRequest : scrappingRequestList){
	            Future<Long> price = webScrapper.getPrice(scrappingRequest.getUrl());
	            while(!price.isDone()){
	                try {
	                    Thread.sleep(100);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	            LOG.info("Elapsed time " + (System.currentTimeMillis() - start));
	            try {
	                Long newPrice = price.get();
	                if(Long.compare(scrappingRequest.getPrice(),0L) == 0){
	                    // BLINDLY UPDATE
	                    scrappingRequest.setPrice(newPrice);
	                    requestRepository.save(scrappingRequest);
	                }
	                else if(newPrice < scrappingRequest.getPrice()){
	                    scrappingRequest.setPrice(newPrice);
	                    // TODO: 22/05/17 Send Email Notification
	                    String emailContent = "Price Dropped from " + scrappingRequest.getPrice() + " to " + newPrice;
	                    notificationClient.sendNotification(scrappingRequest.getEmailId(),emailContent);
	                    scrappingRequest.setSendNotification(true);
	                    requestRepository.save(scrappingRequest);
	                }
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            } catch (ExecutionException e) {
	                e.printStackTrace();
	            }
	        }

	        return null;
	    }
	}

	

