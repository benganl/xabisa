package za.co.wyzetech.xabisa.core.supplier;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.wyzetech.xabisa.api.AbstractNamedEntity;
import za.co.wyzetech.xabisa.core.product.Product;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
public class Supplier extends AbstractNamedEntity {
  private static final long serialVersionUID = 1L;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "supplier",
      cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Set<Product> products;
}
