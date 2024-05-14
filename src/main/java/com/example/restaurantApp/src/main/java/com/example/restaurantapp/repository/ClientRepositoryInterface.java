package com.example.restaurantapp.repository;

import com.example.restaurantapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepositoryInterface extends JpaRepository<Client, Long> {

    @Query(value = "select * from clients order by id", nativeQuery = true)
    List<Client> getAll();
}
