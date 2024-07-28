package com.recrutaibackend.service;

import com.recrutaibackend.dto.AddressRequest;
import com.recrutaibackend.model.Address;
import com.recrutaibackend.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public Address createAddress(AddressRequest request) {
        var address = addressMapper.mapToEntity(request);
        return addressRepository.save(address);
    }

}
