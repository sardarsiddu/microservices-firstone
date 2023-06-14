package com.marvel.DiscovaryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscovaryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscovaryServerApplication.class, args);
	}

}
