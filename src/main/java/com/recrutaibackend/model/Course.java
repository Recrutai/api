package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tbl_courses")
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "sender")
    private String sender;
    @Column(name = "conclusion")
    private Date conclusion;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Course(String name, String description, String sender, Date conclusion, User user) {
        this.name = name;
        this.description = description;
        this.sender = sender;
        this.conclusion = conclusion;
        this.user = user;
    }
}
