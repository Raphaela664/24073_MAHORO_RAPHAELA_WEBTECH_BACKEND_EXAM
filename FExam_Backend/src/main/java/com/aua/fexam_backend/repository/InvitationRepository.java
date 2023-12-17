package com.aua.fexam_backend.repository;

import com.aua.fexam_backend.domain.Assignment;
import com.aua.fexam_backend.domain.Invitation;
import com.aua.fexam_backend.domain.Lecturer;
import com.aua.fexam_backend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> getInvitationByStudent(Student student);
}
