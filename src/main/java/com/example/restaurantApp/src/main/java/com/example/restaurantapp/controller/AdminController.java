package com.example.restaurantapp.controller;

import com.example.restaurantapp.model.Order;
import com.example.restaurantapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/admins", produces = MediaType.APPLICATION_JSON_VALUE)

public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/{orderId}/accept")
    public Order acceptOrder(@PathVariable("orderId") long orderId){
        return adminService.acceptOrder(orderId);
    }

    @PostMapping("/{orderId}/deny")
    public void denyOrder(@PathVariable("orderId") long orderId){
        adminService.denyOrder(orderId);
    }

}
