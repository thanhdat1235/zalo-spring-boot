package zalooa.zalooa.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zalooa.zalooa.model.Customer;
import zalooa.zalooa.model.RegisterCustomerDTO;
import zalooa.zalooa.service.CustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping("/customer")
  public List<Customer> getAllCustomer() {
    return customerService.getAllCustomer();
  }

  @GetMapping("/customer/{id}")
  public Customer getCustomerById(@PathVariable Long id) {
    return customerService.getCustomerById(id);
  }

  @PatchMapping("/customer/{id}")
  public Customer updateUserById(@PathVariable() Long id, @RequestBody
  Customer body) {
    return customerService.updateCustomerById(id, body);
  }
}
