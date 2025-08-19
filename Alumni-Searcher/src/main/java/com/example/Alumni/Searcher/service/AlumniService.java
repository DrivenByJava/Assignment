package com.example.Alumni.Searcher.service;

import com.example.Alumni.Searcher.dto.AlumniProfileResponse;
import com.example.Alumni.Searcher.dto.SearchRequest;
import com.example.Alumni.Searcher.entity.AlumniProfile;
import com.example.Alumni.Searcher.repository.AlumniProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumniService {

    private final AlumniProfileRepository repository;

    public AlumniService(AlumniProfileRepository repository) {
        this.repository = repository;
    }

    public List<AlumniProfileResponse> searchAndSave(SearchRequest request) {
        // Mocked search result for assignment (replace with PhantomBuster later)
        AlumniProfileResponse response = new AlumniProfileResponse(
                "John Doe",
                request.getDesignation(),
                request.getUniversity(),
                "New York, USA",
                "Software Engineer at XYZ Corp",
                request.getPassoutYear()
        );

        // Save to DB
        AlumniProfile entity = new AlumniProfile();
        entity.setName(response.getName());
        entity.setCurrentRole(response.getCurrentRole());
        entity.setUniversity(response.getUniversity());
        entity.setLocation(response.getLocation());
        entity.setLinkedinHeadline(response.getLinkedinHeadline());
        entity.setPassoutYear(response.getPassoutYear());
        repository.save(entity);

        List<AlumniProfileResponse> list = new ArrayList<>();
        list.add(response);
        return list;
    }

    public List<AlumniProfileResponse> getAll() {
        List<AlumniProfile> entities = repository.findAll();
        List<AlumniProfileResponse> responses = new ArrayList<>();

        for (AlumniProfile entity : entities) {
            AlumniProfileResponse r = new AlumniProfileResponse(
                    entity.getName(),
                    entity.getCurrentRole(),
                    entity.getUniversity(),
                    entity.getLocation(),
                    entity.getLinkedinHeadline(),
                    entity.getPassoutYear()
            );
            responses.add(r);
        }
        return responses;
    }
}
