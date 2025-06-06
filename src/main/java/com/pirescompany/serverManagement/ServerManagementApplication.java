package com.pirescompany.serverManagement;

import com.pirescompany.serverManagement.enumerated.Status;
import com.pirescompany.serverManagement.model.Server;
import com.pirescompany.serverManagement.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository repository) {
		return args -> {
			repository.save(new Server(null, "192.168.0.2", "Ubuntu Linux", "16GB", "Personal Desktop", "http://localhost:8080/api/v1/server/image/server1.png", Status.SERVER_UP));
			repository.save(new Server(null, "192.168.0.3", "Windoes", "8GB", "Personal Desktop", "http://localhost:8080/api/v1/server/image/server3.png", Status.SERVER_UP));
			repository.save(new Server(null, "192.168.0.4", "Ubuntu Linux", "64GB", "Server", "http://localhost:8080/api/v1/server/image/server2.png", Status.SERVER_UP));
			repository.save(new Server(null, "192.168.0.5", "Ubuntu Linux", "16GB", "Personal Desktop", "http://localhost:8080/api/v1/server/image/server1.png", Status.SERVER_UP));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		config.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Authorization", "Jwt-Token"));
		config.setExposedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Accept", "Authorization", "Jwt-Token"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
