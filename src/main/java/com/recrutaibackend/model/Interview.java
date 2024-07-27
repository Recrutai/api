package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_interview")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "interviewer_id")
    private Member interviewer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_application_id")
    private Application candidateApplication;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "scheduled_to")
    private OffsetDateTime scheduledTo;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private InterviewModel model;

    @Column(name = "reunion_url")
    private String reunionURL;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by")
    private Member createdBy;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    private Interview(
            Member interviewer,
            Application candidateApplication,
            String title,
            String description,
            OffsetDateTime scheduledTo,
            InterviewModel model,
            String reunionURL,
            Address address,
            Member createdBy
    ) {
        this.interviewer = interviewer;
        this.candidateApplication = candidateApplication;
        this.title = title;
        this.description = description;
        this.scheduledTo = scheduledTo;
        this.model = model;
        this.reunionURL = reunionURL;
        this.address = address;
        this.createdBy = createdBy;
    }

    public static Interview createRemote(
            Member interviewer,
            Application candidateApplication,
            String title,
            String description,
            OffsetDateTime scheduledTo,
            String reunionURL,
            Member createdBy
    ) {
        return new Interview(
                interviewer,
                candidateApplication,
                title,
                description,
                scheduledTo,
                InterviewModel.REMOTE,
                reunionURL,
                null,
                createdBy
        );
    }

    public static Interview createLocal(
            Member interviewer,
            Application candidateApplication,
            String title,
            String description,
            OffsetDateTime scheduledTo,
            Address address,
            Member createdBy
    ) {
        return new Interview(
                interviewer,
                candidateApplication,
                title,
                description,
                scheduledTo,
                InterviewModel.LOCAL,
                null,
                address,
                createdBy
        );
    }
}
