package com.recrutaibackend.service;

import com.recrutaibackend.dto.AddressRequest;
import com.recrutaibackend.dto.AddressResponse;
import com.recrutaibackend.model.Address;
import com.recrutaibackend.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public Address create(AddressRequest addressRequest) {
        var address = addressMapper.mapToEntity(addressRequest);
        return addressRepository.save(address);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public AddressResponse findByStreetAddress(String streetAddress) {
        var address = addressRepository.findByStreetAddress(streetAddress)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found!"));
        return addressMapper.mapToResponse(address);
    }

}
