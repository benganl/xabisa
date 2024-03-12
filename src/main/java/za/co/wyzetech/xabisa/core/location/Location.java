package za.co.wyzetech.xabisa.core.location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.wyzetech.xabisa.api.AbstractNamedEntity;

@Entity
@Table(name = "location")
@Getter @Setter
public class Location extends AbstractNamedEntity {
  private static final long serialVersionUID = 1L;

  @Column(name = "latitude")
  private Float latitude;
  
  @Column(name = "longitude")
  private Float longitude;
}
