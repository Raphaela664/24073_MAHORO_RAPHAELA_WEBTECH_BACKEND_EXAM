package com.aua.fexam_backend.controller;

import com.aua.fexam_backend.domain.Assignment;
import com.aua.fexam_backend.domain.Invitation;
import com.aua.fexam_backend.domain.Lecturer;
import com.aua.fexam_backend.domain.Student;
import com.aua.fexam_backend.repository.InvitationRepository;
import com.aua.fexam_backend.repository.LecturerRepository;
import com.aua.fexam_backend.repository.StudentRepository;
import com.aua.fexam_backend.service.AssignmentService;
import com.aua.fexam_backend.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(
        value = "/assignment",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)

public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private InvitationService invitationService;
public static class AssignmentRequest {
    private Assignment assignment;
    private List<Student> students;

    public  AssignmentRequest(Assignment assignment, List<Student> students) {
        this.assignment = assignment;
        this.students = students;
    }

    public AssignmentRequest() {
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
    @PostMapping(value = "/createAssignment")

    public ResponseEntity<?> createAssignment(@RequestBody AssignmentRequest assignmentRequest ) {
        Assignment assignment = assignmentRequest.getAssignment();
        List<Student> students = assignmentRequest.getStudents();
        if (assignment != null) {
            Lecturer lecturer = lecturerRepository.findById(assignment.getLecturer().getLecturer_id()).get();
            assignment.setLecturer(lecturer);
            Assignment assignment1 = assignmentService.saveAssignment(assignment);

            for ( Student student : students) {

                Student foundStudent = studentRepository.findById(student.getStudent_id()).get();
                if (foundStudent != null) {
                    Invitation invitation = new Invitation();
                    invitation.setStudent(foundStudent);
                    invitation.setAssignment(assignment1);
                    invitationRepository.save(invitation);
                }
           }

            if (assignment1 != null) {
                return new ResponseEntity<>("Assignment saved", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Something is wrong", HttpStatus.BAD_GATEWAY);
            }
        } else {
            return new ResponseEntity<>("Something is wrong where", HttpStatus.BAD_GATEWAY);
        }
    }


    @GetMapping(value = "/getAssignments")
    public ResponseEntity<List<Assignment>> getAssignments() {
        List<Assignment> assignments =assignmentService.GetAllAssignments();
        if (assignments != null && !assignments.isEmpty()) {
            return new ResponseEntity<>(assignments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/getAssignments/{lecturer_id}")
    public ResponseEntity<List<Assignment>> getlecturerAssignments(@PathVariable long lecturer_id) {
        Lecturer lecturer = lecturerRepository.findById(lecturer_id).get();
        List<Assignment> assignments =assignmentService.GetLecturerAssignments(lecturer);
        if (assignments != null && !assignments.isEmpty()) {
            return new ResponseEntity<>(assignments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/ViewSingleAssignment/{assignment_id}")
    public ResponseEntity<Assignment> ViewAssignment(@RequestBody Long assignment_id) {
        Assignment assignment = assignmentService.FindAssignment(assignment_id);
        if (assignment != null ) {
            return new ResponseEntity<>(assignment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping(value = "/GetStudent/{id}")
//    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
//        Student student = studentService.FindStudent(id);
//        if (student != null ) {
//            return new ResponseEntity<>(student, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @DeleteMapping(value = "/deleteAssignment/{assignment_id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long assignment_id){
        System.out.println(assignment_id);

        String message = assignmentService.deleteAssignment(assignment_id);
        if(message!= null){
            return new ResponseEntity<>("Assignment deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Something is wrong", HttpStatus.BAD_GATEWAY);
        }



    }
//    @PutMapping(value = "/UpdateStudent/{id}")
//    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student){
//
//        String message = studentService.UpdateStudent(id, student);
//        if(message!= null){
//            return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>("Something is wrong", HttpStatus.BAD_GATEWAY);
//        }
//
//
//
//    }


}
