package com.springboot.point_of_sale.util.mappers;

import com.springboot.point_of_sale.dto.CustomerDTO;
import com.springboot.point_of_sale.dto.request.CustomerUpdateDTO;
import com.springboot.point_of_sale.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO entityToDTO(Customer customer);

    Customer dtoToEntity(CustomerDTO customerDTO);

    void updateDTOTOEntity(
            CustomerUpdateDTO customerUpdateDTO,
            @MappingTarget Customer customer);

    List<CustomerDTO> entityListToDTOList(List<Customer> customerList);
}
