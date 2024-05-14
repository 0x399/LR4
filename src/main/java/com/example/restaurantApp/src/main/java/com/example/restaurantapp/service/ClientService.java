package com.example.restaurantapp.service;

import com.example.restaurantapp.model.Client;
import com.example.restaurantapp.model.Order;
import com.example.restaurantapp.model.OrderStatus;
import com.example.restaurantapp.repository.ClientRepositoryInterface;
import com.example.restaurantapp.repository.OrderRepositoryInterface;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepositoryInterface clientRepositoryInterface;
    @Autowired
    OrderService orderService;

    public Client create(Client client){
        return clientRepositoryInterface.save(client);
    }

    public Client getById(long clientId){return clientRepositoryInterface.findById(clientId).orElseThrow(EntityNotFoundException::new);}

    public List<Client> getAll(){
        return clientRepositoryInterface.getAll();
    }

    public Order createOrder(long clientId){
        Order order = new Order();
        order.setClient(clientRepositoryInterface.findById(clientId).orElseThrow(EntityExistsException::new));
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATION);
        order.setTotalPrice(0.0);
        return orderService.create(order);
    }
}
