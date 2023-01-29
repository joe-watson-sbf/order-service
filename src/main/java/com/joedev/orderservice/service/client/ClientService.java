package com.joedev.orderservice.service.client;

import com.joedev.orderservice.dto.ClientDto;

import java.util.List;

public interface ClientService {
    void saveClient(ClientDto clientDto);
    void saveClients(List<ClientDto> clientDtoList);
    void updateClient(ClientDto clientDto);
    ClientDto getClientById(Long clientId);
    List<ClientDto> getAllClients();
}
