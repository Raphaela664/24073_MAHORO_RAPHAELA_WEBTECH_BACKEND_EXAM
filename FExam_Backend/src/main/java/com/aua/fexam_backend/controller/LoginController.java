package com.aua.fexam_backend.controller;

import com.aua.fexam_backend.domain.Admin;
import com.aua.fexam_backend.domain.Lecturer;
import com.aua.fexam_backend.domain.Student;
import com.aua.fexam_backend.repository.AdminRepository;
import com.aua.fexam_backend.repository.LecturerRepository;
import com.aua.fexam_backend.repository.StudentRepository;
import com.aua.fexam_backend.service.LecturerService;
import com.aua.fexam_backend.service.PasswordGenerator;
import com.aua.fexam_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(
        value = "/Authentication",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class LoginController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginAccount(@RequestBody Map<String, Object> requestBody){
        String email = (String) requestBody.get("email");
        String password = (String) requestBody.get("password");
        Student student = studentRepository.findStudentByEmail(email);
        Lecturer lecturer = lecturerRepository.findLecturerByEmail(email);
        Admin admin = adminRepository.findAdminByEmail(email);

        if(student != null){
            PasswordGenerator passwordGenerator = new PasswordGenerator();
            boolean passcheck = passwordGenerator.CheckPassword(password,student.getPassword());
            if(passcheck) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
            }
        }
        else if(admin != null){
            if(password.equals(admin.getPassword())){
                return new ResponseEntity<>(admin, HttpStatus.OK);
            }
            return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
        }

        else if(lecturer != null){
            PasswordGenerator passwordGenerator = new PasswordGenerator();
            boolean passcheck = passwordGenerator.CheckPassword(password,lecturer.getPassword());
            if(passcheck) {
                return new ResponseEntity<>(lecturer, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
            }

        }
        else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
