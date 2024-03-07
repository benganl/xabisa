package za.co.wyzetech.xabisa.supplier;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SupplierRepository extends JpaRepository<Supplier, UUID> {

}