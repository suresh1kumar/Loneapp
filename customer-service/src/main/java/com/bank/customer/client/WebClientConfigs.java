package com.bank.customer.client;


import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.bank.customer.client.LoanApplicationClient;

@Configuration
public class WebClientConfigs {

	@Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;
	
	@Bean
    public WebClient loneApplicationWebClient() {
        return WebClient.builder()
                .baseUrl("http://loneapplication-service")
                .filter(filterFunction)
                .build();
    }
	
	@Bean
    public LoanApplicationClient loneApplicationClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(loneApplicationWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(LoanApplicationClient.class);
    }
}
