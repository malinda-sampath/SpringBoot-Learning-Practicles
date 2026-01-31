package com.springboot.point_of_sale.repo;

import com.springboot.point_of_sale.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item, Integer> {
    List<Item> getAllByItemNameAndActiveState (String itemName, Boolean activeState);
}
