package com.example.restaurantapp.repository;

import com.example.restaurantapp.model.Administrator;
import com.example.restaurantapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepositoryInterface extends JpaRepository<Administrator, Long> {
}
