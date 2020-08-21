package com.brewery.client.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.brewery.client.web.model.CustomerDto;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "sfg.customer", ignoreUnknownFields = false)
@Data
public class CustomerClient {

	final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
	String apiHost;
	RestTemplate restTemplate;

	public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public CustomerDto getCustomerById(UUID id) {
		return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + id.toString(), CustomerDto.class);
	}

	public URI saveNewCustomer() {
		CustomerDto customerDto = CustomerDto.builder().name("New Customer").build();
		return restTemplate.postForLocation(apiHost+CUSTOMER_PATH_V1, customerDto);
	}
	
	public void updateCustomer() {
		CustomerDto customerDto = CustomerDto.builder().name("Edited Customer").build();
		UUID id = UUID.randomUUID();
		restTemplate.put(apiHost+CUSTOMER_PATH_V1+id, customerDto);
	}
	
	public void deleteCustomer() {
		restTemplate.delete(apiHost+CUSTOMER_PATH_V1+UUID.randomUUID());
	}
}
