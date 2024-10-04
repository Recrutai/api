package com.recrutai.api.vacancy.interview;

import com.recrutai.api.address.Address;
import com.recrutai.api.organization.member.Member;
import com.recrutai.api.vacancy.application.Application;
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
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "interviewer_id")
    private Member interviewer;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "scheduled_to")
    private OffsetDateTime scheduledTo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "reunion_url")
    private String reunionURL;

    @Column(name = "is_remote")
    private Boolean isRemote;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "created_by")
    private Member createdBy;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    public Interview(
            Application application,
            Member interviewer,
            String title,
            String description,
            OffsetDateTime scheduledTo,
            Address address,
            String reunionURL,
            Boolean isRemote,
            Member createdBy
    ) {
        this.application = application;
        this.interviewer = interviewer;
        this.title = title;
        this.description = description;
        this.scheduledTo = scheduledTo;
        this.address = address;
        this.reunionURL = reunionURL;
        this.isRemote = isRemote;
        this.createdBy = createdBy;
    }

}
