package com.recrutaibackend.institution;

import com.recrutaibackend.address.Address;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_institution_address")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class InstitutionAddress {
    @Id
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    @MapsId
    private Address address;

    public InstitutionAddress(Institution institution, Address address) {
        this.institution = institution;
        this.address = address;
    }

}
