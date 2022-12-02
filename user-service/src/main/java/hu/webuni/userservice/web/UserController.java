package hu.webuni.userservice.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.userservice.api.UserServiceApi;

@RestController
@RequestMapping("/api")
public class UserController implements UserServiceApi{

}
