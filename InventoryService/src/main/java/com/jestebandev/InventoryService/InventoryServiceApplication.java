package com.jestebandev.InventoryService;

import com.jestebandev.InventoryService.model.Inventory;
import com.jestebandev.InventoryService.repository.InventoryRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(
		title = "MicroServices API",
		version = "1.0",
		description = "Real-time microservices project using Spring Boot"))
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return (args) -> {
			Inventory inventory1=new Inventory();
			inventory1.setSkuCode("MOUSE-1");
			inventory1.setQuantity(23);
			Inventory inventory2=new Inventory();
			inventory2.setSkuCode("PC-1");
			inventory2.setQuantity(3);
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}
}
