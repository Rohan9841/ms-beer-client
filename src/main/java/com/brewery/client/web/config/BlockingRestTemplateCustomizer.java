package com.brewery.client.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

	private final int maxConn;
	private final int maxConnPerRoute;
	private final int requestConnnectionTimeOut;
	private final int socketConnnectionTimeOut;

	public BlockingRestTemplateCustomizer(@Value("${connManager.max.con}") int maxConn,
			@Value("${connManager.max.con.perRoute}") int maxConnPerRoute,
			@Value("${request.connection.timeOut}") int requestConnnectionTimeOut,
			@Value("${socket.connection.timeOut}") int socketConnnectionTimeOut) {
		super();
		this.maxConn = maxConn;
		this.maxConnPerRoute = maxConnPerRoute;
		this.requestConnnectionTimeOut = requestConnnectionTimeOut;
		this.socketConnnectionTimeOut = socketConnnectionTimeOut;
	}

	public ClientHttpRequestFactory clientHttpRequestFactory() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(maxConn);
		connectionManager.setDefaultMaxPerRoute(maxConnPerRoute);

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(requestConnnectionTimeOut)
				.setSocketTimeout(socketConnnectionTimeOut).build();

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager)
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()).setDefaultRequestConfig(requestConfig)
				.build();

		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

	@Override
	public void customize(RestTemplate restTemplate) {
		restTemplate.setRequestFactory(this.clientHttpRequestFactory());

	}

}
