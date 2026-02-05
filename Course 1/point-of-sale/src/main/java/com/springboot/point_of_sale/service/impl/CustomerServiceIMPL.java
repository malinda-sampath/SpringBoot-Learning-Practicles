package com.springboot.point_of_sale.service.impl;

import com.springboot.point_of_sale.dto.CustomerDTO;
import com.springboot.point_of_sale.dto.request.CustomerUpdateDTO;
import com.springboot.point_of_sale.entity.Customer;
import com.springboot.point_of_sale.exception.NotFoundException;
import com.springboot.point_of_sale.repo.CustomerRepo;
import com.springboot.point_of_sale.service.CustomerService;
import com.springboot.point_of_sale.util.mappers.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public CustomerServiceIMPL(
            CustomerRepo customerRepo,
            CustomerMapper customerMapper
    ) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerRepo.save(customerMapper.dtoToEntity(customerDTO));
    }

    @Override
    public void updateCustomer(CustomerUpdateDTO customerUpdateDTO) {

        Customer existingCustomer = customerRepo.findById(customerUpdateDTO.getCustomerId())
                .orElseThrow(() ->
                        new NotFoundException(
                                "Customer not found with id: " + customerUpdateDTO.getCustomerId()
                        )
                );
        customerMapper.updateDTOTOEntity(customerUpdateDTO, existingCustomer);
        customerRepo.save(existingCustomer);
    }

    @Override
    public CustomerDTO getById(int customerId) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + customerId));

        return customerMapper.entityToDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return customerMapper.entityListToDTOList(customers);
    }

    @Override
    public void deleteCustomer(int customerId) {
        if (!customerRepo.existsById(customerId)) {
            throw new NotFoundException("Customer not found with id: " + customerId);
        } else {
            customerRepo.deleteById(customerId);
        }
    }

    @Override
    public List<CustomerDTO> getCustomersByActiveStatus(boolean activeStatus) {
        List<Customer> customers = customerRepo.getCustomersByActiveState(activeStatus);
        return customerMapper.entityListToDTOList(customers);
    }
}
