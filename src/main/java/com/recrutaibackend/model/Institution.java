package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tbl_institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String name;
    private String type;
    private String industry;
    @OneToOne(
            mappedBy = "institution",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Address address;
    private int employees;
    private String description;
    private String webSite;
    @OneToMany(
            mappedBy = "institution",
            cascade = CascadeType.ALL
    )
    private Set<Member> members = new HashSet<>();
    @OneToMany(
            mappedBy = "institution",
            cascade = CascadeType.ALL
    )
    private Set<Employment> employments = new HashSet<>();
    @CreationTimestamp
    private LocalDateTime createAt;
}
