package hu.webuni.userservice.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="user-service", url="${feign.userservice.url}")
public interface UserServiceApi {

}
