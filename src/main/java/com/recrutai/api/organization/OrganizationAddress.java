package com.recrutai.api.organization;

import com.recrutai.api.address.Address;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_organization_address")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class OrganizationAddress {
    @Id
    @Setter(AccessLevel.PACKAGE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    @MapsId
    private Address address;

    public OrganizationAddress(Organization organization, Address address) {
        this.organization = organization;
        this.address = address;
    }

}
