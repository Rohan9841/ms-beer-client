package com.brewery.client.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brewery.client.web.model.BeerDto;

@SpringBootTest
class CustomerClientTest {

	@Autowired 
	BeerClient beerClient;
	
	@Test
	void testGetCustomerById() {
		BeerDto beerDto = beerClient.getBeerById(UUID.randomUUID());
		assertNotNull(beerDto);
	}

	@Test
	void testSaveNewCustomer() {
		BeerDto beerDto = BeerDto.builder().beerName("Test Beer").build();
		URI uri = beerClient.saveNewBeer(beerDto);
		assertNotNull(uri);
	}

	@Test
	void testUpdateCustomer() {
		BeerDto beerDto = BeerDto.builder().beerName("Edit test Beer").build();
		UUID uuid = UUID.randomUUID();
		beerClient.updateBeer(uuid, beerDto);
	}

	@Test
	void testDeleteCustomer() {
		beerClient.deleteBeer(UUID.randomUUID());
	}

}
