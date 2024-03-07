package za.co.wyzetech.xabisa.supplier;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.wyzetech.xabisa.api.NamedBaseEntity;
import za.co.wyzetech.xabisa.product.Product;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
public class Supplier extends NamedBaseEntity {
  private static final long serialVersionUID = 1L;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "supplier",
      cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Set<Product> products;
}
