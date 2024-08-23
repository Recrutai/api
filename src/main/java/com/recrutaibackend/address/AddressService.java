package com.recrutaibackend.address;

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