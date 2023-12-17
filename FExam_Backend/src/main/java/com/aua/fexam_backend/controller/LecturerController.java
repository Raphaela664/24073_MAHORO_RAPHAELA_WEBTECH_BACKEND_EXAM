package com.aua.fexam_backend.controller;

import com.aua.fexam_backend.domain.Lecturer;
import com.aua.fexam_backend.domain.Student;
import com.aua.fexam_backend.repository.StudentRepository;
import com.aua.fexam_backend.service.LecturerService;
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
        value = "/lecturer",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MailSenderUtil mail;
    @PostMapping(value = "/saveLecturer")
    public ResponseEntity<?> createLecturer(@RequestBody Lecturer lecturer){
        if (lecturer != null){
            Student student = studentRepository.findStudentByEmail(lecturer.getEmail());
            if (student == null) {
            PasswordGenerator passwordGenerator = new PasswordGenerator();
            String password = passwordGenerator.generatePassword();
            lecturer.setPassword(passwordGenerator.encryptPassword(password));
            String message = lecturerService.saveLecturer(lecturer);
            if(message!= null) {
                    String to = lecturer.getEmail();
                    String subject = "account creation";
                    String body = "Congratulations, Your Lecturer account was created successfully." + " You can use you email " + lecturer.getEmail()
                            + " and your password " + password + " to access your account";
                    mail.sendEmail(to, subject, body);
                    return new ResponseEntity<>("Lecturer saved", HttpStatus.CREATED);
            }
            else {
                    return new ResponseEntity<>("Something is wrong", HttpStatus.BAD_GATEWAY);
            }
            }else {
                return new ResponseEntity<>("Something is wrong", HttpStatus.IM_USED);
            }
        }else{
            return new ResponseEntity<>("Something is wrong", HttpStatus.BAD_GATEWAY);
        }

    }
    @GetMapping(value = "/getLecturers")
    public ResponseEntity<List<Lecturer>> getStudents() {
        List<Lecturer> lecturers = lecturerService.GetAllLecturers();
        if (lecturers != null && !lecturers.isEmpty()) {
            return new ResponseEntity<>(lecturers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
