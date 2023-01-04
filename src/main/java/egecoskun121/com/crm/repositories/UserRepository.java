package egecoskun121.com.crm.repositories;


import egecoskun121.com.crm.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query(value = "SELECT * FROM USERS WHERE USER_NAME=(:username)", nativeQuery = true)
    User getUserByUserName(@Param("username") String userName);

    @Query(value = "SELECT * FROM USERS ORDER BY ID DESC", nativeQuery = true)
    List<User> getAllUsersOrderedById();

    @Query(value = "SELECT EMAIL FROM USERS", nativeQuery = true)
    List<String> getAllEmails();

    @Query(value = "SELECT USER_NAME FROM USERS", nativeQuery = true)
    List<String> getAllUsernames();


}
