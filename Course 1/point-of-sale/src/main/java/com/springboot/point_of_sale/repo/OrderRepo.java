package com.springboot.point_of_sale.repo;

import com.springboot.point_of_sale.dto.queryInterface.OrderDetailsInterface;
import com.springboot.point_of_sale.dto.response.OrderDetailsDTO;
import com.springboot.point_of_sale.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query(
            value = "SELECT c.customer_name,c.customer_address,c.contact_numbers,o.order_date,o.total " +
                    "FROM customer c, orders o " +
                    "WHERE o.active_state=?1 " +
                    "AND c.customer_id=o.customer_id",
            nativeQuery = true
    )
    Page<OrderDetailsInterface> getAllOrderDetails(boolean status, Pageable pageable);
}
