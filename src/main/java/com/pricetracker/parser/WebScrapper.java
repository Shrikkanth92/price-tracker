package com.pricetracker.parser;

import java.util.concurrent.Future;

import org.jsoup.nodes.Document;



public interface WebScrapper {

	   Future<Document> readURL(String url);

	    /**
	     * Read URL AND RETURN THE PRICE OF PRODUCT
	     * **/
	    Future<Long> getPrice(String url);



	    /**
	     * READ URL AND RETURNS THE AVAILABILITY OF THE PRODUCT EITHER AS IN STOCK OR OUT OF STOCK
	     * **/
	    String getAvailability(String url);
}
