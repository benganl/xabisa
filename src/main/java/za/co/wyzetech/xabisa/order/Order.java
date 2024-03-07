package za.co.wyzetech.xabisa.order;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.wyzetech.xabisa.api.BaseEntity;
import za.co.wyzetech.xabisa.customer.Customer;
import za.co.wyzetech.xabisa.product.Product;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToMany
  @JoinTable(name = "order_products",
      joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
  private Set<Product> products;
}