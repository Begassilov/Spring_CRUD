package com.springCRUD.repository;

import com.springCRUD.model.Book;
import com.springCRUD.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Integer id);
}
