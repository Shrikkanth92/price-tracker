package com.pricetracker.action;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pricetracker.dto.CronRequest;
import com.pricetracker.dto.ScrappingRequest;
import com.pricetracker.repositories.RequestRepository;


@Service
public class UpdatePriceAction implements Action<ResponseEntity>{
	
	@Autowired
	private RequestRepository requestRepository;
	
	@NotBlank
    private Long price;

    private CronRequest cronRequest;
	
    public UpdatePriceAction withPrice(Long price){
        this.price = price;
        return this;
    }

    public UpdatePriceAction withScrappingRequest(CronRequest cronRequest){
        this.cronRequest = cronRequest;
        return this;
    }
    
    @Override
	public ResponseEntity invoke() {
    	 List<ScrappingRequest> requestList = requestRepository.findByUrl(cronRequest.getUrl());
         for (ScrappingRequest sRequest : requestList){
             Long oldPrice = sRequest.getPrice();
             if(price < oldPrice){
                 sRequest.setPrice(price);
                 sRequest.setSendNotification(true);
                 // TODO: 22/05/17 Trigger Email Notification from request Object
                 requestRepository.save(sRequest);
             }
             else if(Long.compare(sRequest.getPrice(),0) == 0){
                 // FIRST TIME CRON UPDATE
                 sRequest.setPrice(price);
                 requestRepository.save(sRequest);
             }
         }

         return ResponseEntity.ok("Done");
     }
	
    

}
