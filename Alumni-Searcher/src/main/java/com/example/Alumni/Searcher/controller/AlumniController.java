package com.example.Alumni.Searcher.controller;

import com.example.Alumni.Searcher.dto.ApiResponse;
import com.example.Alumni.Searcher.dto.AlumniProfileResponse;
import com.example.Alumni.Searcher.dto.SearchRequest;
import com.example.Alumni.Searcher.service.AlumniService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumni")
public class AlumniController {

    private final AlumniService service;


    public AlumniController(AlumniService service) {
        this.service = service;
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse<List<AlumniProfileResponse>>> search(
            @Valid @RequestBody SearchRequest request) {
        List<AlumniProfileResponse> results = service.searchAndSave(request);
        return ResponseEntity.ok(new ApiResponse<List<AlumniProfileResponse>>("success", results));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<AlumniProfileResponse>>> all() {
        List<AlumniProfileResponse> results = service.getAll();
        return ResponseEntity.ok(new ApiResponse<List<AlumniProfileResponse>>("success", results));
    }
}
