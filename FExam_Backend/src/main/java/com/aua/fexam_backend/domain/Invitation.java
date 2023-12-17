package com.aua.fexam_backend.domain;

import jakarta.persistence.*;

@Entity
@Table(name="invitation")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invitation_id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    public Invitation() {
    }

    public Invitation(Long invitation_id, Student student, Assignment assignment) {
        this.invitation_id = invitation_id;
        this.student = student;
        this.assignment = assignment;
    }

    public Long getInvitation_id() {
        return invitation_id;
    }

    public void setInvitation_id(Long invitation_id) {
        this.invitation_id = invitation_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
