package com.aua.fexam_backend.service;

import com.aua.fexam_backend.domain.Lecturer;
import com.aua.fexam_backend.domain.Student;
import com.aua.fexam_backend.repository.LecturerRepository;
import com.aua.fexam_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService {
    @Autowired
    LecturerRepository lecturerRepository;
    public String saveLecturer(Lecturer lecturer){
        if (lecturer != null){
            lecturerRepository.save(lecturer);
            return "Lecturer created successfully";
        }else{
            return null;
        }
    }
    public List<Lecturer> GetAllLecturers(){
        return lecturerRepository.findAll();
    }
}
