package za.co.wyzetech.xabisa.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.wyzetech.xabisa.api.AbstractNamedEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class SysRole extends AbstractNamedEntity {
  private static final long serialVersionUID = -1L;

  @Column(name = "description")
  private String description;
}
