package com.joedev.orderservice.controller;

import com.joedev.orderservice.dto.ClientDto;
import com.joedev.orderservice.service.client.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getClients(){
        return ResponseEntity.ok(service.getAllClients());
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long clientId){
        return ResponseEntity.ok(service.getClientById(clientId));
    }
}
