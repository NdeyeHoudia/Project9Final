package com.openclassrooms.geteway_service;

import com.openclassrooms.geteway_service.model.AppRole;
import com.openclassrooms.geteway_service.model.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
@EnableEurekaServer
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

/*
@Bean
CommandLineRunner start(AccountService accountService){
		return  args -> {
			accountService.addNewRole(new AppRole(null,"USER"));
			accountService.addNewRole(new AppRole(null,"ADMIN"));
			accountService.addNewRole(new AppRole(null,"CUSTOMER_MANAGER"));
			accountService.addNewRole(new AppRole(null,"BILLS_MANAGER"));

			accountService.addNewUser(new AppUser(null,"user1","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"admin","1234",new ArrayList<>()));

			accountService.addNewUser(new AppUser(null,"user2","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"user3","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"user4","1234",new ArrayList<>()));

			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("admin","ADMIN");
			accountService.addRoleToUser("user2","USER");
			accountService.addRoleToUser("user2","CUSTOMER_MANAGER");

			accountService.addRoleToUser("user4","USER");
			accountService.addRoleToUser("user4","PRODUCT_MANAGER");

		};
}*/
}
