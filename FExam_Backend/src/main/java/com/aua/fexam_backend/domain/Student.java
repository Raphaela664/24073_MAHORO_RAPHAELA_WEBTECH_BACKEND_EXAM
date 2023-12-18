package com.aua.fexam_backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role ="STUDENT";

    @OneToMany(mappedBy = "student")
    @JsonBackReference
    private List<Invitation> invitations;

    @OneToMany(mappedBy = "student")
    @JsonBackReference
    private List<Submission> submissions;



    public Student() {
    }

    public Student(Long student_id, String name, String email, String password, String role, List<Invitation> invitations, List<Submission> submissions) {
        this.student_id = student_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.invitations = invitations;
        this.submissions = submissions;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
}
