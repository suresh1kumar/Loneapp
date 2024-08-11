package com.bank.customer.dto;

import com.bank.customer.entity.Customer;

public class CustomMapper {

	public static CustomerDTO toDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        //customerDTO.setId(customer.getId());
        customerDTO.setNationalIdentityNumber(customer.getNationalIdentityNumber());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMonthlyIncome(customer.getMonthlyIncome());
        customerDTO.setGender(customer.getGender());
        customerDTO.setAge(customer.getAge());
        customerDTO.setLoanScore(customer.getLoanScore());
        //customerDTO.setLoanApplications(customer.getLoanApplications());
        return customerDTO;
    }

    public static Customer toEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        //customer.setId(customerDTO.getId());
        customer.setNationalIdentityNumber(customerDTO.getNationalIdentityNumber());
//        customer.setNationalIdentityNumber(customerDTO.getNationalIdentityNumber());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setMonthlyIncome(customerDTO.getMonthlyIncome());
        customer.setGender(customerDTO.getGender());
        customer.setAge(customerDTO.getAge());
        customer.setLoanScore(customerDTO.getLoanScore());
        //customer.setLoanApplications(customerDTO.getLoanApplications());
        return customer;
    }
}
