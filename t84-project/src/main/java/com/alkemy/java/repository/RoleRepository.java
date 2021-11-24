package com.alkemy.java.repository;

import com.alkemy.java.dto.IDTOQuery.IRolesDTOName;
import com.alkemy.java.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{

    @Query ( value = "select r.name from roles r where r.name like %:name%",nativeQuery = true)
    IRolesDTOName findName(@Param("name") String name);

    Role findByName(String name);
}
