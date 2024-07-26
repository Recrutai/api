package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tbl_interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Timestamp scheduledTo;
    private String model;
    private String reunionURL;
    @OneToOne(
            mappedBy = "interview",
            cascade = CascadeType.ALL
    )
    private Address address;
    @CreationTimestamp
    private LocalDateTime createAt;
}
