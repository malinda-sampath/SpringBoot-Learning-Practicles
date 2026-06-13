package com.springboot.point_of_sale.controller;

import com.springboot.point_of_sale.dto.CustomerDTO;
import com.springboot.point_of_sale.dto.request.CustomerUpdateDTO;
import com.springboot.point_of_sale.service.CustomerService;
import com.springboot.point_of_sale.util.response.ResponseBuilder;
import com.springboot.point_of_sale.util.response.StandardResponse;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StandardResponse<Void>> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return ResponseBuilder.created(
                "Customer saved successfully",
                null
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        customerService.updateCustomer(customerUpdateDTO);
        return ResponseBuilder.noContent();
    }

    @GetMapping(path = "/get-by-id", params = "id")
    public ResponseEntity<StandardResponse<CustomerDTO>> getCustomerById(@RequestParam(value = "id") int customerId) {
        return ResponseBuilder.ok(
                "Customer retrieved successfully",
                customerService.getById(customerId)
        );
    }

    @GetMapping(path = "/get-all-customers")
    public ResponseEntity<StandardResponse<List<CustomerDTO>>> getAllCustomers() {
        return ResponseBuilder.ok(
                "Customers retrieved successfully",
                customerService.getAllCustomers()
        );
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseBuilder.noContent();
    }

    @GetMapping(path = "/get-by-active-status", params = "status")
    public ResponseEntity<StandardResponse<List<CustomerDTO>>> getCustomersByActiveStatus(@RequestParam(value = "status") boolean activeStatus) {
        return ResponseBuilder.ok(
                "Customers retrieved successfully",
                customerService.getCustomersByActiveStatus(activeStatus)
        );
    }
}
