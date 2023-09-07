package zalooa.zalooa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AdminController {

  @GetMapping("/admin")
  public String adminString() {
    return "Admin level";
  }

}
