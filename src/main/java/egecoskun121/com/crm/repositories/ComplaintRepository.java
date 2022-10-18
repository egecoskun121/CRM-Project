package egecoskun121.com.crm.repositories;

import egecoskun121.com.crm.model.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
}
