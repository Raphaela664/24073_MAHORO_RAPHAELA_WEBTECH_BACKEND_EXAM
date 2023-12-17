package com.aua.fexam_backend.controller;

import com.aua.fexam_backend.domain.Lecturer;
import com.aua.fexam_backend.domain.Student;
import com.aua.fexam_backend.repository.LecturerRepository;
import com.aua.fexam_backend.repository.StudentRepository;
import com.aua.fexam_backend.service.MailSenderUtil;
import com.aua.fexam_backend.service.PasswordGenerator;
import com.aua.fexam_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(
        value = "/student",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    LecturerRepository lecturerRepository;
    @Autowired
    private MailSenderUtil mail;
    @PostMapping(value = "/saveStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        if (student != null){
            Lecturer lecturer = lecturerRepository.findLecturerByEmail(student.getEmail());
            if(lecturer == null) {
                PasswordGenerator passwordGenerator = new PasswordGenerator();
                String password = passwordGenerator.generatePassword();
                student.setPassword(passwordGenerator.encryptPassword(password));
                String message = studentService.saveStudent(student);
                if (message != null) {

                    String to = student.getEmail();
                    String subject = "account creation";
                    String body = "Congratulations, Your student account was created successfully." + " You can use you email " + student.getEmail()
                            + " and your password " + password + " to access your account";
                    mail.sendEmail(to, subject, body);
                    return new ResponseEntity<>("Student saved", HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>("Something is wrong", HttpStatus.BAD_GATEWAY);
                }
            }
            else{
                return new ResponseEntity<>("Email already exists", HttpStatus.IM_USED);
            }
        }else{
            return new ResponseEntity<>("Something is wrong", HttpStatus.BAD_GATEWAY);
        }

    }
    @GetMapping(value = "/getStudents")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.GetAllStudents();
        if (students != null && !students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
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
//    @DeleteMapping(value = "/DeleteStudent/{id}")
//    public ResponseEntity<?> removeStudent(@PathVariable Long id){
//
//        String message = studentService.deleteStudent(id);
//        if(message!= null){
//            return new ResponseEntity<>("Student deleted", HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>("Something is wrong", HttpStatus.BAD_GATEWAY);
//        }
//
//
//
//    }
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
