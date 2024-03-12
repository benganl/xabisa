package za.co.wyzetech.xabisa.core.product;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.wyzetech.xabisa.api.AbstractNamedEntity;
import za.co.wyzetech.xabisa.core.order.Order;
import za.co.wyzetech.xabisa.core.supplier.Supplier;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends AbstractNamedEntity {
  private static final long serialVersionUID = 1L;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Float price;

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private Supplier supplier;

  @ManyToMany(mappedBy = "products")
  private Set<Order> orders;
}
