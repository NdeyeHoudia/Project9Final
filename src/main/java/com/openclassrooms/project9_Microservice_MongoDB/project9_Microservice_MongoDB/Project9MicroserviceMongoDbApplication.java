package com.openclassrooms.project9_Microservice_MongoDB.project9_Microservice_MongoDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Project9MicroserviceMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project9MicroserviceMongoDbApplication.class, args);
	}

}
