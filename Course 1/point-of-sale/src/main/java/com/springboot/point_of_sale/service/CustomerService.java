package com.springboot.point_of_sale.service;

import com.springboot.point_of_sale.dto.CustomerDTO;
import com.springboot.point_of_sale.dto.request.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {
    public void saveCustomer(CustomerDTO customerDTO);
    public void updateCustomer(CustomerUpdateDTO customerUpdateDTO);
    public CustomerDTO getById(int customerId);
    public List<CustomerDTO> getAllCustomers();
    public void deleteCustomer(int customerId);
    public List<CustomerDTO> getCustomersByActiveStatus(boolean activeStatus);
}
