package com.recrutaibackend.mappers;

import com.recrutaibackend.dto.AddressRequest;
import com.recrutaibackend.dto.AddressResponse;
import com.recrutaibackend.model.Address;
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
