package com.recrutaibackend.address;

import org.springframework.stereotype.Service;

@Service
public class AddressMapper {

    public Address mapToEntity(AddressRequest request) {
        return new Address(
                request.streetAddress(),
                request.city(),
                request.state(),
                request.country(),
                request.postalCode(),
                request.latitude(),
                request.longitude()
        );
    }

    public AddressResponse mapToResponse(Address entity) {
        return new AddressResponse(
                entity.getId(),
                entity.getStreetAddress(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getPostalCode(),
                entity.getLatitude(),
                entity.getLongitude()
        );
    }

}
