package com.beer.client.web.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sfg.customer", ignoreUnknownFields = false)
public class CustomerClient {

	final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
}
