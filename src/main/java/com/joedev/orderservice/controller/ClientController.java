package com.joedev.orderservice.controller;

import com.joedev.orderservice.dto.ClientDto;
import com.joedev.orderservice.dto.ResponseDto;
import com.joedev.orderservice.service.client.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/list")
    public ResponseEntity<ResponseDto> addClients(@RequestBody List<ClientDto> clientDto){
        service.saveClients(clientDto);
        return ResponseEntity.ok(new ResponseDto("Clients saved successfully!"));
    }
}
