package hu.webuni.catalogservice.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="catalog-service", url="${feign.catalofg-service.url}")
public interface CatalogServiceApi {

}
