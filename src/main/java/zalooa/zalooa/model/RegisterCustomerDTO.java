package zalooa.zalooa.model;

public class RegisterCustomerDTO {

  private String username;
  private String email;
  private String password;

  public RegisterCustomerDTO() {
  }

  public RegisterCustomerDTO(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String toString() {
    return "Registration info: username: " + this.username + "password: " + this.password;
  }
}
