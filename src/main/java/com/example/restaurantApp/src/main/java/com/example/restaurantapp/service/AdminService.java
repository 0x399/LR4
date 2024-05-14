package com.example.restaurantapp.service;

import com.example.restaurantapp.model.Client;
import com.example.restaurantapp.model.Order;
import com.example.restaurantapp.model.OrderStatus;
import com.example.restaurantapp.repository.AdminRepositoryInterface;
import com.example.restaurantapp.repository.ClientRepositoryInterface;
import com.example.restaurantapp.repository.OrderRepositoryInterface;
import com.netflix.discovery.DiscoveryClient;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepositoryInterface adminRepositoryInterface;
    @Autowired
    OrderRepositoryInterface orderRepositoryInterface;
    @Autowired
    ClientRepositoryInterface clientRepositoryInterface;

    public Order acceptOrder(Long orderId){
        Order order = orderRepositoryInterface.findById(orderId).orElseThrow(EntityNotFoundException::new);
        if (order.getStatus() == OrderStatus.AWAITING_ACCEPTANCE && order.getClient().getBalance() >= order.getTotalPrice()) {
            order.setStatus(OrderStatus.COMPLETED);
            Client client = order.getClient();
            client.setBalance(client.getBalance() - order.getTotalPrice());
            clientRepositoryInterface.save(client);
        }
        return orderRepositoryInterface.save(order);
    }

    public void denyOrder(Long orderId){
        Order order = orderRepositoryInterface.findById(orderId).orElseThrow(EntityNotFoundException::new);
        orderRepositoryInterface.delete(order);
    }
}
