package com.aua.fexam_backend.controller;

import com.aua.fexam_backend.domain.Assignment;
import com.aua.fexam_backend.domain.Invitation;
import com.aua.fexam_backend.domain.Lecturer;
import com.aua.fexam_backend.domain.Student;
import com.aua.fexam_backend.repository.LecturerRepository;
import com.aua.fexam_backend.repository.StudentRepository;
import com.aua.fexam_backend.service.AssignmentService;
import com.aua.fexam_backend.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(
        value = "/invitation",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class InvitationController {
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/getInvitations/{student_id}")
    public ResponseEntity<List<Invitation>> getStudentInvitation(@PathVariable long student_id) {
        Student student = studentRepository.findById(student_id).get();
        List<Invitation> invitations =invitationService.GetStudentInvitations(student);
        if (invitations != null && !invitations.isEmpty()) {
            return new ResponseEntity<>(invitations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
