package com.recrutaibackend.institution.member;

import com.recrutaibackend.institution.Institution;
import com.recrutaibackend.auth.user.User;
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
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @Column(name = "role")
    private String role;

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

    public Member(User user, Institution institution, String role, Member addedBy) {
        this.user = user;
        this.institution = institution;
        this.role = role;
        this.addedBy = addedBy;
    }
}
