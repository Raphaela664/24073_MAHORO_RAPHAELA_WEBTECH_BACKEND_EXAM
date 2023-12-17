package com.aua.fexam_backend.repository;


import com.aua.fexam_backend.domain.Assignment;
import com.aua.fexam_backend.domain.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
   List<Assignment> getAssignmentByLecturer(Lecturer lecturer);

}
