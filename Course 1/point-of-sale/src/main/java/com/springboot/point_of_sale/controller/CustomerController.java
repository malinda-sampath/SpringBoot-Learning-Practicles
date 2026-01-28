package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.CustomerDTO;
import com.springboot.point_of_sale.dto.request.CustomerUpdateDTO;
import com.springboot.point_of_sale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        return customerService.updateCustomer(customerUpdateDTO);
    }

    @GetMapping(path = "/get-by-id",params = "id")
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId) {
        return customerService.getById(customerId);
    }

    @GetMapping(path = "/get-all-customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @GetMapping(path = "/get-by-active-status", params = "status")
    public List<CustomerDTO> getCustomersByActiveStatus(@RequestParam(value = "status") boolean activeStatus) {
        return customerService.getCustomersByActiveStatus(activeStatus);
    }
}
