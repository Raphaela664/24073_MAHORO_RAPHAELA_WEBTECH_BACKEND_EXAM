package com.aua.fexam_backend.domain;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Entity
@Table(name="submission")
public class Submission {

    @Id
    private long submission_id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Assignment assignment;
    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Student student;


    private String file_url;

    public Submission() {
    }

    public Submission(long submission_id, Assignment assignment, Student student) {
        this.submission_id = submission_id;
        this.assignment = assignment;
        this.student = student;
    }

    public long getSubmission_id() {
        return submission_id;
    }

    public void setSubmission_id(long submission_id) {
        this.submission_id = submission_id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
