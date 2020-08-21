package com.beer.client.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.beer.client.web.model.BeerDto;

@SpringBootTest
class BeerClientTest {

	@Autowired
	BeerClient beerClient;
	
	@Test
	void testGetBeerById() {
		BeerDto beerDto = beerClient.getBeerById(UUID.randomUUID());
		assertNotNull(beerDto);
	}

	@Test
	void testSaveNewBeer() {
		BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();
		URI uri = beerClient.saveNewBeer(beerDto);
		assertNotNull(uri);
		System.out.println(uri.toString());
	}
	
	@Test
	void testUpdateBeer() {
		BeerDto beerDto = BeerDto.builder().beerName("New name").build();
		beerClient.updateBeer(UUID.randomUUID(), beerDto);
	}
	
	@Test
	void testDeleteBeer() {
		beerClient.deleteBeer(UUID.randomUUID());
	}
}
