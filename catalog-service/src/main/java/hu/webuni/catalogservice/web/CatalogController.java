package hu.webuni.catalogservice.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.catalogservice.api.CatalogServiceApi;

@RestController
@RequestMapping("/api")
public class CatalogController implements CatalogServiceApi{

}
