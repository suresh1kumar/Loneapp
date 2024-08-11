package com.bank.customer.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.bank.customer.dto.LoanApplicationDto;

@HttpExchange
public interface LoanApplicationClient {
	
	    
	    @GetExchange("/loneapp/customer/{nationalIdentityNumber}")
        public List<LoanApplicationDto> findByNationalIdentityNumber(@PathVariable("nationalIdentityNumber") String nationalIdentityNumber);

}




//public LoanApplication(Long id,Customer customer, Loan loan,Notification notification,Loan loan) 



