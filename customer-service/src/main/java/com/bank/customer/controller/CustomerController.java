package com.bank.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.customer.client.LoanApplicationClient;
import com.bank.customer.dto.CustomerDTO;
import com.bank.customer.dto.LoanApplicationDto;
import com.bank.customer.entity.Customer;
import com.bank.customer.service.CustomerService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private LoanApplicationClient loanApplicationClient;
	private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return new ResponseEntity(customerService.getAllCustomers(), HttpStatus.OK);
    }
    
    @PostMapping("/submit-loan-application")
    public String submitLoanApplication(@RequestBody Customer customer) {
    	customerService.addCustomer(customer);
    	LoanApplicationDto lo=new LoanApplicationDto();
    	lo.setNationalIdentityNumber(customer.getNationalIdentityNumber());
        return "Save successfully....";
    }

  //


    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody Customer customerDTO) {
        customerService.addCustomer(customerDTO);
        
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    
   
    @GetMapping("/with-employees")
    public List<CustomerDTO> findAllWithEmployees() {
        
    	
        List<Customer> customers
                = customerService.getAllCustomers();
        List<CustomerDTO> c1=new ArrayList<CustomerDTO>();
        List<CustomerDTO> c2=new ArrayList<CustomerDTO>();
        
        for (Customer ca : customers) {
        	CustomerDTO c=new CustomerDTO();
			c.setNationalIdentityNumber(ca.getNationalIdentityNumber());
			c.setFirstName(ca.getFirstName());
			c.setLastName(ca.getLastName());
			c.setPhone(ca.getPhone());
			c.setEmail(ca.getEmail());
			c.setMonthlyIncome(ca.getMonthlyIncome());
			c.setGender(ca.getGender());
			c.setAge(ca.getAge());
			c.setLoanScore(ca.getLoanScore());
			c1.add(c);
		}
        c2.addAll(c1);
        c2.forEach(department ->
        department.setLoanApplicationsDto(
                		loanApplicationClient.findByNationalIdentityNumber(department.getNationalIdentityNumber())));
        return  c2;
    }


}
