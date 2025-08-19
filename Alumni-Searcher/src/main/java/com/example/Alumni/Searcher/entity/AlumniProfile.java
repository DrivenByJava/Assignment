package com.example.Alumni.Searcher.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "alumni_profiles")
public class AlumniProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "\"current_role\"", nullable = false)
    private String currentRole;

    @Column(nullable = false)
    private String university;

    private String location;

    @Column(length = 512)
    private String linkedinHeadline;

    private Integer passoutYear;

    public AlumniProfile() {}

    public AlumniProfile(Long id, String name, String currentRole, String university,
                         String location, String linkedinHeadline, Integer passoutYear) {
        this.id = id;
        this.name = name;
        this.currentRole = currentRole;
        this.university = university;
        this.location = location;
        this.linkedinHeadline = linkedinHeadline;
        this.passoutYear = passoutYear;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCurrentRole() { return currentRole; }
    public void setCurrentRole(String currentRole) { this.currentRole = currentRole; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getLinkedinHeadline() { return linkedinHeadline; }
    public void setLinkedinHeadline(String linkedinHeadline) { this.linkedinHeadline = linkedinHeadline; }

    public Integer getPassoutYear() { return passoutYear; }
    public void setPassoutYear(Integer passoutYear) { this.passoutYear = passoutYear; }
}
