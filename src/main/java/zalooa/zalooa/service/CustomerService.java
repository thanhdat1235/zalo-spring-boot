package zalooa.zalooa.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zalooa.zalooa.model.Customer;
import zalooa.zalooa.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.info("In the user detail service");
    return customerRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User is not valid"));
  }

  public List<Customer> getAllCustomer() {
    return customerRepository.findAll();
  }

  public Customer getCustomerById(Long id) {
    return customerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public Customer updateCustomerById(Long id, Customer body) {
    Customer customer = customerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    if (body.getUsername() != null) {
      customer.setUsername(body.getUsername());
    }
    if (body.getPassword() != null) {
      customer.setPassword(body.getPassword());
    }
    if (body.getEmail() != null) {
      customer.setEmail(body.getEmail());
    }
      customerRepository.save(customer);
    customer.setPassword(null);
      return customer;

  }
}
