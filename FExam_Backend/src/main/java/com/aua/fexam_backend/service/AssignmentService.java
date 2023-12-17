package com.aua.fexam_backend.service;

import com.aua.fexam_backend.domain.Assignment;
import com.aua.fexam_backend.domain.Lecturer;
import com.aua.fexam_backend.repository.AssignmentRepository;
import com.aua.fexam_backend.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;
    public Assignment saveAssignment(Assignment assignment){
        if (assignment != null){

           assignmentRepository.save(assignment);
            return assignment;
        }else{
            return null;
        }
    }
    public List<Assignment> GetAllAssignments(){
        return assignmentRepository.findAll();

    }
    public List<Assignment> GetLecturerAssignments(Lecturer lecturer){
        return assignmentRepository.getAssignmentByLecturer(lecturer);

    }

    public Assignment FindAssignment(Long assignment_id){
        if (assignment_id != null) {
           Assignment assignment = assignmentRepository.findById(assignment_id).get();
           return assignment;
        }
        else{
            return null;
        }
    }
    public String deleteAssignment(Long assignment_id){
        if (assignment_id != null){
            assignmentRepository.deleteById(assignment_id);
            return "assignment deleted successfully";
        }else{
            return null;
        }
    }


    public String UpdateAssignment(Assignment assignment){
        if (assignment != null) {
            Assignment assignmentFound = assignmentRepository.findById(assignment.getAssignment_id()).get();
            assignmentFound.setAssignment_description(assignment.getAssignment_description());
            assignmentFound.setDeadline(assignment.getDeadline());
            assignmentRepository.save(assignmentFound);
            return "assignmemt updated successfully";
        }
        else{
            return null;
        }
    }
}
