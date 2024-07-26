package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tbl_vacations")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    private String title;
    private String description;
    private String workModel;
    private Double avgSalary;
    private int positions;
    private int applications;
    private int publishedBy;
    private Timestamp publishedAt;
    private Timestamp closedAt;
    private Timestamp closedBy;




}
