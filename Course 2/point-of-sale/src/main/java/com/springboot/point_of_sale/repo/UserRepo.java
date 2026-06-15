package com.springboot.point_of_sale.repo;

import com.springboot.point_of_sale.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email);
}
