package com.example.restaurantapp.repository;


import com.example.restaurantapp.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepositoryInterface extends JpaRepository<Meal, Long> {

    @Query(value = "select * from meals order by id", nativeQuery = true)
    List<Meal> getAll();

    Meal getMealById(long id);

    Meal getMealByName(String name);
}
