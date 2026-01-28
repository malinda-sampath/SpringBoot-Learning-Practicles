package com.springboot.point_of_sale.service.impl;

import com.springboot.point_of_sale.dto.CustomerDTO;
import com.springboot.point_of_sale.dto.request.CustomerUpdateDTO;
import com.springboot.point_of_sale.entity.Customer;
import com.springboot.point_of_sale.repo.CustomerRepo;
import com.springboot.point_of_sale.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerServiceIMPL(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getContactNumber(),
                customerDTO.getNic(),
                customerDTO.isActiveState()
        );
        customerRepo.save(customer);
        return "Customer Saved Successfully";
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if (customerRepo.existsById(customerUpdateDTO.getCustomerId())) {
            Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());
            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());

            customerRepo.save(customer);

            return "Customer Updated Successfully";
        } else {
            throw new RuntimeException("Customer Not Found");
        }
    }

    @Override
    public CustomerDTO getById(int customerId) {
        if (customerRepo.existsById(customerId)) {
            Customer customer = customerRepo.getReferenceById(customerId);

            return new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActiveState()
            );
        } else {
            throw new RuntimeException("Customer Not Found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();

        if (customers.isEmpty()){
            throw new RuntimeException("No customers found");
        } else {
            List<CustomerDTO> customerDTOS = new ArrayList<>();
            for (Customer customer : customers) {
                CustomerDTO customerDTO = new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomerSalary(),
                        customer.getContactNumber(),
                        customer.getNic(),
                        customer.isActiveState()
                );
                customerDTOS.add(customerDTO);
            }

            return customerDTOS;
        }
    }

    @Override
    public String deleteCustomer(int customerId) {
        if (customerRepo.existsById(customerId)) {
            Customer customer = customerRepo.getReferenceById(customerId);
            customerRepo.delete(customer);
            return "Customer Deleted Successfully";
        } else {
            throw new RuntimeException("Customer Not Found");
        }
    }

    @Override
    public List<CustomerDTO> getCustomersByActiveStatus(boolean activeStatus) {
        List<Customer> customers = customerRepo.getCustomersByActiveState(activeStatus);
        if (customers.isEmpty()){
            throw new RuntimeException("No customers found");
        } else {
            List<CustomerDTO> customerDTOS = new ArrayList<>();

            for (Customer customer : customers){
                CustomerDTO customerDTO = new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomerSalary(),
                        customer.getContactNumber(),
                        customer.getNic(),
                        customer.isActiveState()
                );

                customerDTOS.add(customerDTO);
            }

            return customerDTOS;
        }
    }
}
