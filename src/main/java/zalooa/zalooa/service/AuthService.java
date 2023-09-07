package zalooa.zalooa.service;

import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zalooa.zalooa.model.Customer;
import zalooa.zalooa.model.LoginResponseDTO;
import zalooa.zalooa.model.Role;
import zalooa.zalooa.repository.CustomerRepository;
import zalooa.zalooa.repository.RoleRepository;

@Service
@Transactional
public class AuthService {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private TokenService tokenService;

  public Customer registerCustomer(String username, String password, String email) {
    String encodePassword = passwordEncoder.encode(password);

    Role customerRole = roleRepository.findByAuthority("USER").orElseThrow();

    Set<Role> authorities = new HashSet<>();

    authorities.add(customerRole);

    return customerRepository.save(new Customer(0L, username, email, encodePassword, authorities));
  }

  public LoginResponseDTO loginUser(String username, String password){

    try{
      Authentication auth = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password)
      );

      String token = tokenService.generateJwt(auth);

      tokenService.saveToken(username, token);

      return new LoginResponseDTO(customerRepository.findByUsername(username).orElseThrow(), token);

    } catch(AuthenticationException e){
      return new LoginResponseDTO(null, "");
    }
  }

}
