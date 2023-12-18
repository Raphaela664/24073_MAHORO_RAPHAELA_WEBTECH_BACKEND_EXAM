package com.aua.fexam_backend.service;

import com.aua.fexam_backend.domain.Lecturer;
import com.aua.fexam_backend.domain.Submission;
import com.aua.fexam_backend.repository.LecturerRepository;
import com.aua.fexam_backend.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SubmissionService {
    @Autowired
    SubmissionRepository submissionRepository;
    public String saveSubmission(Submission submission){
        if (submission != null){
            submissionRepository.save(submission);
            return "Submission created successfully";
        }else{
            return null;
        }
    }
    public List<Submission> GetAllSubmissions(){
        return submissionRepository.findAll();
    }
}
