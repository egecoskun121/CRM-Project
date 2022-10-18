package egecoskun121.com.crm.repositories;


import egecoskun121.com.crm.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
