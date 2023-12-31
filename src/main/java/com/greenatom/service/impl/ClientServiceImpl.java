package com.greenatom.service.impl;

import com.greenatom.domain.dto.ClientDTO;
import com.greenatom.domain.entity.Client;
import com.greenatom.domain.mapper.ClientMapper;
import com.greenatom.repository.ClientRepository;
import com.greenatom.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final Logger log = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDTO> findAll() {
        log.debug("Request to get all Clients");
        return clientMapper.toDto(clientRepository.findAll());
    }

    @Override
    public Optional<ClientDTO> findOne(Long id) {
        log.debug("Request to get Client : {}", id);
        return Optional.ofNullable(clientMapper.toDto(clientRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Request not found with id: " + id))));
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        log.debug("Request to save client : {}", clientDTO);
        Client client = clientMapper.toEntity(clientDTO);
        clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO updateClient(ClientDTO client) {
        log.debug("Request to partially update Client : {}", client);
        return clientRepository
                .findById(client.getId())
                .map(existingEvent -> {
                    clientMapper.partialUpdate(existingEvent, client);

                    return existingEvent;
                })
                .map(clientRepository::save)
                .map(clientMapper::toDto).orElseThrow(
                        EntityNotFoundException::new);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository
                .findById(id)
                .ifPresent(client -> {
                    clientRepository.delete(client);
                    log.debug("Deleted Client: {}", client);
                });
    }
}
