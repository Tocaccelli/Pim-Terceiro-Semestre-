package com.localfarm.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Startup {
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
}