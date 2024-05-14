package com.example.restaurantapp.controller;

import com.example.restaurantapp.model.Order;
import com.example.restaurantapp.response.responseDTO;
import com.example.restaurantapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("{orderId}")
    public Order showOrder(@PathVariable("orderId") long orderID) throws Exception {
        return orderService.getById(orderID);
    }

    @PostMapping("/{customerId}/create")
    public ResponseEntity<responseDTO> createOrder(@PathVariable("customerId") long customerId){
        orderService.createWithId(customerId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new responseDTO("200", "Created successfully."));
    }

    @PostMapping("/{orderId}/add/{mealId}")
    public ResponseEntity<responseDTO> addMealToOrder(@PathVariable("orderId") long orderId, @PathVariable("mealId") long mealId){
        orderService.addMeal(mealId, orderId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new responseDTO("200", "Created successfully."));
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<responseDTO> deleteOrder(@PathVariable("orderId") long orderId){
        orderService.delete(orderId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new responseDTO("200", "Deleted successfully."));
    }

    @PatchMapping("/{orderId}/delete/{mealId}")
    public ResponseEntity<responseDTO> deleteMeal(@PathVariable("orderId") long orderId, @PathVariable("mealId") long mealId){
        orderService.removeMeal(mealId, orderId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new responseDTO("200", "Meal deleted successfully."));
    }

    @PostMapping("/{orderId}/complete")
    public Order completeOrder(@PathVariable("orderId") long orderId){
        return orderService.complete(orderId);
    }
}
