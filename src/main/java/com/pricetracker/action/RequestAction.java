package com.pricetracker.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pricetracker.dto.Request;
import com.pricetracker.dto.ResponsePayload;
import com.pricetracker.repositories.RequestRepository;

@Service
public class RequestAction implements Action<ResponsePayload>{
	
	
	@Autowired
    private RequestRepository requestRepository;
	
    private Request request;

	@Override
	public ResponsePayload invoke() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public RequestAction withRequest(Request request){
	        this.request = request;
	        return this;
	    }

}
