package com.aua.fexam_backend.service;

import com.aua.fexam_backend.domain.Assignment;
import com.aua.fexam_backend.domain.Invitation;
import com.aua.fexam_backend.domain.Lecturer;
import com.aua.fexam_backend.domain.Student;
import com.aua.fexam_backend.repository.InvitationRepository;
import com.aua.fexam_backend.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {
    @Autowired
    InvitationRepository invitationRepository;
    public String saveInvitation(Invitation invitation){
        if (invitation != null){
            invitationRepository.save(invitation);
            return "Invitation created successfully";
        }else{
            return null;
        }
    }

    public List<Invitation> GetStudentInvitations(Student student){
        return invitationRepository.getInvitationByStudent(student);
    }
}
