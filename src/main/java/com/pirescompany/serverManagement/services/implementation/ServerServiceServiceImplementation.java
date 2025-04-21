package com.pirescompany.serverManagement.services.implementation;

import com.pirescompany.serverManagement.enumerated.Status;
import com.pirescompany.serverManagement.model.Server;
import com.pirescompany.serverManagement.repository.ServerRepository;
import com.pirescompany.serverManagement.services.interfaces.ServerServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServerServiceServiceImplementation implements ServerServiceInterface {

    private final ServerRepository repository;

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return repository.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);

        Server server = repository.findByIpAddress(ipAddress);
        InetAddress address = Inet4Address.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        repository.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return repository.findAll(Pageable.ofSize(limit)).stream().toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by ID: {}", id);
        return repository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return repository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {}", id);
        repository.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServerImageUrl() {
        String[] imagesNames = {"server1.png", "server2.png", "server3.png"};
        var uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("server/image/" + imagesNames[new Random().nextInt(3)]);
        return uri.toUriString();
    }
}
