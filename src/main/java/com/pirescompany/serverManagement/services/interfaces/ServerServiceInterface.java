package com.pirescompany.serverManagement.services.interfaces;

import com.pirescompany.serverManagement.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerServiceInterface {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);

}
