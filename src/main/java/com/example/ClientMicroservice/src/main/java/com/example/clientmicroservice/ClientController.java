package com.example.clientmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/{clientId}")
    public com.example.clientmicroservice.Client showClient(@PathVariable("clientId") long clientID) throws Exception {
        return clientService.getById(clientID);
    }

    @PatchMapping("/{clientId}/money/{costs}")
    public Client changeFunds(@PathVariable("clientId") long clientID, @PathVariable("costs") long costs){
        com.example.clientmicroservice.Client client = clientService.getById(clientID);
        client.setBalance((double)costs);
        return clientService.create(client);
    }
}
