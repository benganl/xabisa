package za.co.wyzetech.xabisa.security;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<User, UUID> {
  Optional<User> findByUsername(String username);
}
