package hu.webuni.orderservice.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="order-service", url="${feign.order-service.url}")
public interface OrderServiceApi {

}
