package com.recrutaibackend.address;

import org.springframework.stereotype.Service;

@Service
public class AddressMapper {

    public Address mapToEntity(AddressRequest request) {
        if (request == null) return null;
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

    public Address mapToEntity(CityAddressRequest request) {
        if (request == null) return null;
        return new Address(
                null,
                request.city(),
                request.state(),
                request.country(),
                null,
                null,
                null
        );
    }

    public AddressResponse mapToResponse(Address entity) {
        if (entity == null) return null;
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
