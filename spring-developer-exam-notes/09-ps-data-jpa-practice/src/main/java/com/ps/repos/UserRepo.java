package com.ps.repos;

import com.ps.ents.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//DONE 44. Complete the definition of this interface to make the tests in TestUserRepo.java pass.
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username like %?1%")
    List<User> findAllByUsername(String username);

    User findOneByUsername(@Param("un") String username);

    String findUsernameById(Long id);

    @Query(value = "SELECT COUNT(u) FROM User u")
    long countUsers();

}