package com.alkemy.java.repository;

import com.alkemy.java.dto.IDTOQuery.IUserDTO;
import com.alkemy.java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query(value = "select * from users u where u.id =:id and u.deleted =false",nativeQuery = true)
    public Optional<User> findById(@Param("id") Long id);
    @Query(value = "select u.first_name, u.last_name, u.email, u.photo from users u where u.deleted =false",nativeQuery = true )
    public List<IUserDTO> getUsers();


}
