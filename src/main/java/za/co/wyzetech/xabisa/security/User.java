package za.co.wyzetech.xabisa.security;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.wyzetech.xabisa.api.BaseEntity;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {
  private static final long serialVersionUID = -1L;

  @Column(name = "username", insertable = true, unique = true)
  private String username;

  @Column(name = "pssword")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", nullable = false),
      inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
  private Set<Role> roles = new HashSet<>();
}
