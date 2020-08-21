package com.brewery.client.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.brewery.client.web.model.BeerDto;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "sfg.beer", ignoreUnknownFields=false)
@Data
public class BeerClient {
	final String BEER_PATH_V1 = "/api/v1/beer/";
	final RestTemplate restTemplate;
	String apiHost;
	
	public BeerClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public BeerDto getBeerById(UUID id) {
		return restTemplate.getForObject(apiHost+BEER_PATH_V1+id.toString(), BeerDto.class	);
	}
	
	public URI saveNewBeer(BeerDto beerDto) {
		return restTemplate.postForLocation(apiHost+BEER_PATH_V1, beerDto);
	}
	
	public void updateBeer(UUID uuid, BeerDto beerDto) {
		restTemplate.put(apiHost+BEER_PATH_V1+uuid.toString(), beerDto);
	}
	
	public void deleteBeer(UUID uuid) {
		restTemplate.delete(apiHost+BEER_PATH_V1+uuid.toString());
	}
}
