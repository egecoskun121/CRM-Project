package egecoskun121.com.crm.repositories;

import egecoskun121.com.crm.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
