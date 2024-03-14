package za.co.wyzetech.xabisa.core.order;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.wyzetech.xabisa.api.AbstractIdentifiableEntity;
import za.co.wyzetech.xabisa.core.customer.Customer;
import za.co.wyzetech.xabisa.core.product.Product;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends AbstractIdentifiableEntity {
  private static final long serialVersionUID = 1L;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer user;

  @ManyToMany
  @JoinTable(name = "order_products",
      joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
  private Set<Product> products;
}