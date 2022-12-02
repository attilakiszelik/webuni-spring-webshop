package hu.webuni.shipperservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import hu.webuni.catalogservice.api.CatalogServiceApi;
import hu.webuni.orderservice.api.OrderServiceApi;
import hu.webuni.userservice.api.UserServiceApi;

@SpringBootApplication
@EnableFeignClients(basePackageClasses= {CatalogServiceApi.class, OrderServiceApi.class, UserServiceApi.class})
public class ShipperServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipperServiceApplication.class, args);
	}

}
