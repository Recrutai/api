package com.recrutaibackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_address")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "latitude", columnDefinition = "numeric(8, 6)")
    private Double latitude;

    @Column(name = "longitude", columnDefinition = "numeric(9, 6)")
    private Double longitude;

    public Address(
            String streetAddress,
            String city,
            String state,
            String country,
            String postalCode,
            Double latitude,
            Double longitude
    ) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
