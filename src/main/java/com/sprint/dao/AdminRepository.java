package com.sprint.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.dto.LoginDTO;  // Ensure this import is correct
import com.sprint.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM Admin a WHERE LOWER(a.email) = LOWER(:email)")
    Boolean emailExist(@Param("email") String email);

    // Change this method to return LoginDTO
    @Query("SELECT new com.sprint.dto.LoginDTO(a.id, a.email, a.name) FROM Admin a WHERE LOWER(a.email) = LOWER(:email) AND a.password = :password")
    LoginDTO getAdminDetails(@Param("email") String email, @Param("password") String password);
}
