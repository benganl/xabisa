package za.co.wyzetech.xabisa.api;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractIdentifiableEntity implements Identifiable {
  private static final long serialVersionUID = 1L;

  @Id
  // @GeneratedValue(strategy = GenerationType.UUID, generator = "uuid2")
  @GeneratedValue(generator = "uuid2")
  protected UUID id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_created")
  protected Date createdDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_updated")
  protected Date updateDate;

  @PrePersist
  void prePersist() {
    if (Objects.isNull(id)) {
      id = UUID.randomUUID();
    }

    if (Objects.isNull(createdDate)) {
      createdDate = new Date();
    }

    if (Objects.isNull(updateDate)) {
      updateDate = new Date();
    }
  }

  @PreUpdate
  void preUpdate() {
    updateDate = new Date();
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof AbstractIdentifiableEntity))
      return false;
    AbstractIdentifiableEntity other = (AbstractIdentifiableEntity) obj;
    return Objects.equals(id, other.id);
  }
}
