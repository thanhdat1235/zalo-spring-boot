package zalooa.zalooa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zalooa.zalooa.model.Customer;
import zalooa.zalooa.model.LoginResponseDTO;
import zalooa.zalooa.model.RegisterCustomerDTO;
import zalooa.zalooa.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public Customer registerCustomer(@RequestBody RegisterCustomerDTO body) {
    return authService.registerCustomer(body.getUsername(), body.getPassword(), body.getEmail());
  }

  @PostMapping("/login")
  public LoginResponseDTO loginUser(@RequestBody RegisterCustomerDTO body){
    return authService.loginUser(body.getUsername(), body.getPassword());
  }
}
