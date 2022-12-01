package hu.webuni.userservice.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="user-service", url="${feign.user-service.url}")
public interface UserServiceApi {

}
