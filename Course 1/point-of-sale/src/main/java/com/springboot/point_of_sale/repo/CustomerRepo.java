package com.springboot.point_of_sale.repo;

import com.springboot.point_of_sale.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    List<Customer> getCustomersByActiveState(boolean activeState);

//    @Query("""
//        SELECT c
//        FROM Customer c
//        WHERE c.activeState = :activeState
//    """)
//    List<Customer> findByActiveState(@Param("activeState") boolean activeState);
}
