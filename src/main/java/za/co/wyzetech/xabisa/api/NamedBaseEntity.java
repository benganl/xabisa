package za.co.wyzetech.xabisa.api;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class NamedBaseEntity extends BaseEntity implements IdentifiableNamedEntity {
  private static final long serialVersionUID = 1L;

  @Column(name = "name")
  protected String name;
}
