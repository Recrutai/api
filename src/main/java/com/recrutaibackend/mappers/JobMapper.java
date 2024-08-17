package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.JobRequest;
import com.recrutaibackend.dto.JobResponse;
import com.recrutaibackend.model.Job;
import com.recrutaibackend.model.User;
import org.springframework.stereotype.Service;

@Service
public class JobMapper {

    public Job mapToEntity(JobRequest request, User user) {
        return new Job(
                request.title(),
                request.typeJob(),
                request.modalityJob(),
                request.companyName(),
                request.city(),
                request.dateStart(),
                request.dateEnd(),
                request.currentJob(),
                user
        );
    }

    public JobResponse mapToResponse(Job job) {
        return new JobResponse(
                job.getTitle(),
                job.getTypeJob(),
                job.getModalityJob(),
                job.getCompanyName(),
                job.getCity(),
                job.getDateStart(),
                job.getEndStart(),
                job.getCurrentJob()
        );
    }
}
