package hu.webuni.shipperservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.catalogservice.api.CatalogServiceApi;
import hu.webuni.orderservice.api.OrderServiceApi;
import hu.webuni.userservice.api.UserServiceApi;

@RestController
public class ShippingController {

	@Autowired
	CatalogServiceApi catalogServiceApi;
	@Autowired
	OrderServiceApi orderServiceApi;
	@Autowired
	UserServiceApi userServiceApi;
	
}
