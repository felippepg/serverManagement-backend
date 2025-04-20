package com.pirescompany.serverManagement.repository;

import com.pirescompany.serverManagement.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
}
