package com.example.restaurantapp.controller;

import com.example.restaurantapp.model.Client;
import com.example.restaurantapp.model.Order;
import com.example.restaurantapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/{clientId}")
    public Client showClient(@PathVariable("clientId") long clientID) throws Exception {
        return clientService.getById(clientID);
    }

    @PatchMapping("/{clientId}/money/{costs}")
    public Client changeFunds(@PathVariable("clientId") long clientID, @PathVariable("costs") long costs){
        Client client = clientService.getById(clientID);
        client.setBalance((double)costs);
        return clientService.create(client);
    }
}
