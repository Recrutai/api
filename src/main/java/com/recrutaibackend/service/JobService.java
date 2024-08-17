package com.recrutaibackend.service;

import com.recrutaibackend.dto.JobRequest;
import com.recrutaibackend.dto.JobResponse;
import com.recrutaibackend.mappers.JobMapper;
import com.recrutaibackend.repository.JobRepository;
import com.recrutaibackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository,
                      JobMapper jobMapper,
                      UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.userRepository = userRepository;
    }

    public JobResponse create(JobRequest request) {
        var user = userRepository.findById(request.userId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );
        var job = jobMapper.mapToEntity(request, user);
        jobRepository.save(job);
        return jobMapper.mapToResponse(job);
    }

    public List<JobResponse> getAll(int id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );
        var jobs = jobRepository.findAllByUser(user);
        return jobs.stream()
                .map(jobMapper::mapToResponse)
                .toList();
    }

    public void delete(int id) {
        var job = jobRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found!")
        );;
        jobRepository.delete(job);
    }
}
