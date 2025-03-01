package com.bank.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bank.customer.client.LoanApplicationClient;
import com.bank.customer.dto.LoanApplicationDto;
import com.bank.customer.entity.Customer;
import com.bank.customer.repository.CustomerRepository;

import reactor.core.publisher.Mono;

@Service
public class CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;

    //@Autowired
   // private WebClient webClient;
    @Autowired
    private WebClient webClient;

    @Autowired
    public CustomerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build(); // Base URL for LoanApplicationService
    }
    
    public Mono<LoanApplicationDto> addLoanCustomer(LoanApplicationDto loanApplication) {
    	System.out.println("mommmmmmmmmmmmmm");
        return webClient.post()
                .uri("/loneapp/add-loan-customer") // Endpoint to which the POST request will be sent
                .bodyValue(loanApplication) // Set the body of the request
                .retrieve()
                .bodyToMono(LoanApplicationDto.class) // Expected response type
                .doOnError(e -> System.err.println("Error: " + e.getMessage())) // Handle errors
                .onErrorResume(e -> Mono.empty()); // Optionally handle errors
    }

   public void addCustomer(Customer customer) {
	   
	  // LoanApplicationDto l= loanApplicationClient.findByNationalIdentityNumber(customer.getNationalIdentityNumber());
	   customerRepository.save(customer);
	   LoanApplicationDto lo=new LoanApplicationDto();
   	   lo.setNationalIdentityNumber(customer.getNationalIdentityNumber());
	   addLoanCustomer(lo);
	   System.out.println("--------------------------:");
   }
   
   public List<Customer> getAllCustomers(){
	   return customerRepository.findAll();
   }

}
