package com.recrutaibackend.controller;

import com.recrutaibackend.dto.AddressRequest;
import com.recrutaibackend.dto.AddressResponse;
import com.recrutaibackend.service.AddressMapper;
import com.recrutaibackend.service.AddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public AddressController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @PostMapping
    ResponseEntity<AddressResponse> create(@RequestBody @Valid AddressRequest addressRequest) {
        var address = addressService.createAddress(addressRequest);
        return ResponseEntity.ok(addressMapper.mapToResponse(address));
    }

    @GetMapping
    ResponseEntity<List<AddressResponse>> getAll() {
        var address = addressService.getAllAddress()
                .stream()
                .map(addressMapper::mapToResponse)
                .toList();
        return ResponseEntity.ok(address);
    }

    @GetMapping("/search")
    ResponseEntity<AddressResponse> getAddressByStreet(@RequestParam @NotBlank String streetName) {
        var address = addressService.getAddressByStreet(streetName);
        return ResponseEntity.ok(address);
    }
}
