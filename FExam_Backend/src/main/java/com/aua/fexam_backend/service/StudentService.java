package com.aua.fexam_backend.service;

import com.aua.fexam_backend.domain.Student;
import com.aua.fexam_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

@Autowired
    StudentRepository studentRepository;
    public String saveStudent(Student student){
        if (student != null){
            studentRepository.save(student);
            return "Student created successfully";
        }else{
            return null;
        }
    }
    public List<Student> GetAllStudents(){
        return studentRepository.findAll();
    }

}
