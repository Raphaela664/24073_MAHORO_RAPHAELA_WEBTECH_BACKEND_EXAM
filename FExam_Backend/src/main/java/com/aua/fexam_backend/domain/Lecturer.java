package com.aua.fexam_backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="lecturer")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecturer_id;
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role ="LECTURER";

    @OneToMany(mappedBy = "lecturer")
    @JsonBackReference
    private List<Assignment> assignments;

    public String getRole() {
        return role;
    }

    public Lecturer(Long lecturer_id, String name, String email, String password, String role, List<Assignment> assignments) {
        this.lecturer_id = lecturer_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.assignments = assignments;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Lecturer() {
    }


    public Long getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(Long lecturer_id) {
        this.lecturer_id = lecturer_id;
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


}
