package com.recrutaibackend.service;

import com.recrutaibackend.dto.AddressRequest;
import com.recrutaibackend.mappers.AddressMapper;
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

    public Address create(AddressRequest addressRequest) {
        var address = addressMapper.mapToEntity(addressRequest);
        return addressRepository.save(address);
    }

}
