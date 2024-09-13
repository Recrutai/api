package com.recrutai.api.address;

import org.springframework.stereotype.Service;

@Service
public class AddressMapper {

    public Address mapToEntity(AddressRequest request) {
        if (request == null) return null;
        return new Address(
                request.getStreetAddress(),
                request.getCity(),
                request.getState(),
                request.getCountry(),
                request.getPostalCode(),
                request.getLatitude(),
                request.getLongitude()
        );
    }

    public Address mapToEntity(CityAddressRequest request) {
        if (request == null) return null;
        return new Address(
                null,
                request.getCity(),
                request.getState(),
                request.getCountry(),
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

    public CityAddressResponse mapToCityResponse(Address entity) {
        if (entity == null) return null;
        return new CityAddressResponse(
                entity.getId(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry()
        );
    }

}
