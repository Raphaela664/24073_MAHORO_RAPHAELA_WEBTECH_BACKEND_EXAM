package com.aua.fexam_backend.repository;

import com.aua.fexam_backend.domain.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    Lecturer findLecturerByEmail(String email);

}
