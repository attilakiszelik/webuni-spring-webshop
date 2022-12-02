package hu.webuni.orderservice.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.orderservice.api.OrderServiceApi;

@RestController
@RequestMapping("/api")
public class OrderController implements OrderServiceApi{

}
