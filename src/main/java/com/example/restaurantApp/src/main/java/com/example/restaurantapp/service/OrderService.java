package com.example.restaurantapp.service;

import com.example.restaurantapp.model.Meal;
import com.example.restaurantapp.model.Order;
import com.example.restaurantapp.model.OrderStatus;
import com.example.restaurantapp.repository.ClientRepositoryInterface;
import com.example.restaurantapp.repository.MealRepositoryInterface;
import com.example.restaurantapp.repository.OrderRepositoryInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {

    @Autowired
    OrderRepositoryInterface orderRepositoryInterface;
    @Autowired
    ClientRepositoryInterface clientRepositoryInterface;
    @Autowired
    MealRepositoryInterface mealRepositoryInterface;

    public Order create(Order order){
        return orderRepositoryInterface.save(order);
    }

    public List<Order> getAll(){
        return orderRepositoryInterface.getAll();
    }

    public Order getById(Long id) throws Exception {
        return orderRepositoryInterface.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Order addMeal(Long mealId, Long orderId){
        Order order = orderRepositoryInterface.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.getMealList().add(mealRepositoryInterface.findById(mealId).orElseThrow());
        order.setTotalPrice(order.getTotalPrice() + mealRepositoryInterface.getMealById(mealId).getPrice());
        return orderRepositoryInterface.save(order);
    }

    public void delete(long id) {
        orderRepositoryInterface.delete(orderRepositoryInterface.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public Order createWithId(long clientId){
        Order order = new Order();
        order.setClient(clientRepositoryInterface.findById(clientId).orElseThrow());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATION);
        return create(order);
    }

    public Order complete(Long orderId){
        Order order = orderRepositoryInterface.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.setStatus(OrderStatus.AWAITING_ACCEPTANCE);
        return orderRepositoryInterface.save(order);
    }

    public Order removeMeal(Long mealId, Long orderId) {
        // Retrieve the order from the database
        Order order = orderRepositoryInterface.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

        // Retrieve the meal from the database
        Meal meal = mealRepositoryInterface.findById(mealId)
                .orElseThrow(EntityNotFoundException::new);

        // Remove the meal from the order's meal list
        order.getMealList().remove(meal);

        // Update the total price of the order
        order.setTotalPrice(order.getTotalPrice() - meal.getPrice());

        // Save and return the updated order
        return orderRepositoryInterface.save(order);
    }


}
