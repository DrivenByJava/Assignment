package com.example.Alumni.Searcher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class SearchRequest {

    @NotBlank(message = "University is required")
    private String university;

    @NotBlank(message = "Designation is required")
    private String designation;

    @Positive(message = "Passout year must be positive")
    private Integer passoutYear; // optional

    public SearchRequest() {}

    public SearchRequest(String university, String designation, Integer passoutYear) {
        this.university = university;
        this.designation = designation;
        this.passoutYear = passoutYear;
    }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public Integer getPassoutYear() { return passoutYear; }
    public void setPassoutYear(Integer passoutYear) { this.passoutYear = passoutYear; }
}
