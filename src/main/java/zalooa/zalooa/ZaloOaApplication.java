package zalooa.zalooa;

import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import zalooa.zalooa.model.Customer;
import zalooa.zalooa.model.Role;
import zalooa.zalooa.repository.CustomerRepository;
import zalooa.zalooa.repository.RoleRepository;

@SpringBootApplication
public class ZaloOaApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZaloOaApplication.class, args);
  }

  @Bean
  CommandLineRunner run(RoleRepository roleRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      if(roleRepository.findByAuthority("ADMIN").isPresent()) {
        return;
      }
      Role adminRole = roleRepository.save(new Role("ADMIN"));
      roleRepository.save(new Role("USER"));

      Set<Role> roles = new HashSet<>();
      roles.add(adminRole);

      Customer admin = new Customer(1L, "admin", "admin@gmail.com", passwordEncoder.encode("admin"), roles);

      customerRepository.save(admin);
    };
  }
}
