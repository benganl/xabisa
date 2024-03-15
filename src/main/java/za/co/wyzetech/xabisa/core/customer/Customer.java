package za.co.wyzetech.xabisa.core.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.wyzetech.xabisa.api.AbstractNamedEntity;
import za.co.wyzetech.xabisa.security.SysUser;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer extends AbstractNamedEntity {
  private static final long serialVersionUID = 1L;

  @Column(name = "_name", nullable = true)
  private String name;
  
  @Column(name = "first_name", nullable = true)
  private String firstName;
  
  @Column(name = "last_name", nullable = true)
  private String lastName;
  
  @Column(name = "email", nullable = true)
  private String email;
  
  @Column(name = "mobile_number", nullable = true)
  private String mobile;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private SysUser user;
}
