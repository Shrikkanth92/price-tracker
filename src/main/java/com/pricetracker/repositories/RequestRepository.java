package com.pricetracker.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pricetracker.dto.ScrappingRequest;

public interface RequestRepository extends CrudRepository<ScrappingRequest, Long> {

	 	ScrappingRequest findByEmailId(Long id);

	    List<ScrappingRequest> findByUrl(String url);

	    List<ScrappingRequest> findBySendNotification(Boolean sendNotification);
}
