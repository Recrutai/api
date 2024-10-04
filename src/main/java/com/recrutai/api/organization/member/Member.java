package com.recrutai.api.organization.member;

import com.recrutai.api.auth.user.User;
import com.recrutai.api.organization.Organization;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_member")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "added_by_id")
    private Member addedBy;

    @CreationTimestamp
    @Column(name = "added_at")
    private Instant addedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "removed_by_id")
    private Member removedBy;

    @Column(name = "removed_at")
    private Instant removedAt;

    public Member(User user, Organization organization, MemberRole role, Member addedBy) {
        this.user = user;
        this.organization = organization;
        this.role = role;
        this.addedBy = addedBy;
    }

}
