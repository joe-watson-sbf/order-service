package com.joedev.orderservice.service.client;

import com.joedev.orderservice.dto.ClientDto;
import com.joedev.orderservice.entity.Client;
import com.joedev.orderservice.handler.BusinessException;
import com.joedev.orderservice.mapper.DataMapper;
import com.joedev.orderservice.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.joedev.orderservice.utils.ServiceUtils.requiredNonNonEmptyList;
import static com.joedev.orderservice.utils.ServiceUtils.requiredNonNullObject;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository repository;
    private final DataMapper mapper;

    public ClientServiceImpl(ClientRepository repository, DataMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void saveClient(ClientDto clientDto) {
        requiredNonNullObject(clientDto);
        repository.save(mapper.dtoToEntity(clientDto));
    }

    @Override
    public void saveClients(List<ClientDto> clientDtoList) {
        requiredNonNonEmptyList(clientDtoList);
        List<Client> clients = clientDtoList.stream().map(mapper::dtoToEntity).toList();
        repository.saveAll(clients);
    }

    @Override
    public void updateClient(ClientDto clientDto) {
        requiredNonNullObject(clientDto);
        // VERIFY IF THE USER EXIST TO UPDATE
        getClient(clientDto.getId());
        repository.save(mapper.dtoToEntity(clientDto));
    }

    @Override
    public ClientDto getClientById(Long clientId) {
        return mapper.entityToDto(getClient(clientId));
    }

    @Override
    public List<ClientDto> getAllClients() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto).toList();
    }



    private Client getClient(Long clientId){
        return repository.findById(clientId)
                .orElseThrow(()->
                        new BusinessException("Client with id " + clientId + " not found!",
                                HttpStatus.NOT_FOUND));
    }


}
