package egecoskun121.com.crm.repositories;

import egecoskun121.com.crm.model.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Query(nativeQuery = true, name = "Complaint.complaintResult")
    List<Complaint> findAllComplaintsByUsername(@Param("username") String username);

}
