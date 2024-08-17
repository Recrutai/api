package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tbl_jobs")
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "type_job")
    @Enumerated(EnumType.STRING)
    private TypeJob typeJob;
    @Column(name = "modality")
    @Enumerated(EnumType.STRING)
    private ModalityJob modalityJob;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "city")
    private String city;
    @Column(name = "start_date")
    private Date dateStart;
    @Column(name = "end_date")
    private Date endStart;
    @Column(name = "current_job")
    private Boolean currentJob;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Job(
        String title,
        TypeJob typeJob,
        ModalityJob modalityJob,
        String companyName, String city,
        Date dateStart,
        Date endStart,
        Boolean currentJob,
        User user)
    {
        this.title = title;
        this.typeJob = typeJob;
        this.modalityJob = modalityJob;
        this.companyName = companyName;
        this.city = city;
        this.dateStart = dateStart;
        this.endStart = endStart;
        this.currentJob = currentJob;
        this.user = user;
    }
}
