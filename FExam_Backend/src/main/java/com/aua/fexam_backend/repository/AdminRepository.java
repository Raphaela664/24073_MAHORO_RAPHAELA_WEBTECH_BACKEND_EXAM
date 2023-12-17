package com.aua.fexam_backend.repository;

import com.aua.fexam_backend.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Long> {
    Admin findAdminByEmail(String email);
}
