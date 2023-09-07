package zalooa.zalooa.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "role_id")
  private Integer roleId;

  private String authority;


  public Role() {
    super();
    // TODO Auto-generated constructor stub
  }


  public Role(String authority) {
    this.authority = authority;
  }


  public Role(Integer roleId, String authority) {
    super();
    this.roleId = roleId;
    this.authority = authority;
  }


  public Integer getRoleId() {
    return roleId;
  }


  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }


  public void setAuthority(String authority) {
    this.authority = authority;
  }


  @Override
  public String getAuthority() {
    return this.authority;
  }

}