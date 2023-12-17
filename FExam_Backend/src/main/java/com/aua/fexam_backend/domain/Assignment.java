package com.aua.fexam_backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long assignment_id;
    private String title;
    private String assignment_description;

    private Date deadline;


    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;


    @OneToMany(mappedBy = "assignment")
    @JsonBackReference
    private List<Invitation> invitations;

    public Assignment() {
    }

    public Assignment(long assignment_id, String title, String assignment_description, Date deadline, Lecturer lecturer, List<Invitation> invitations) {
        this.assignment_id = assignment_id;
        this.title = title;
        this.assignment_description = assignment_description;
        this.deadline = deadline;
        this.lecturer = lecturer;
        this.invitations = invitations;
    }

    public long getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(long assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssignment_description() {
        return assignment_description;
    }

    public void setAssignment_description(String assignment_description) {
        this.assignment_description = assignment_description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }
}
