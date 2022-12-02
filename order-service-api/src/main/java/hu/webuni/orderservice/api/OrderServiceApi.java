package hu.webuni.orderservice.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="order-service", url="${feign.orderservice.url}")
public interface OrderServiceApi {

}
