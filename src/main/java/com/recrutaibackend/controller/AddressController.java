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
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    AddressController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @PostMapping
    ResponseEntity<AddressResponse> create(@RequestBody @Valid AddressRequest addressRequest) {
        var address = addressMapper.mapToResponse(addressService.create(addressRequest));
        return ResponseEntity.ok(address);
    }

    @GetMapping
    ResponseEntity<List<AddressResponse>> findAll() {
        var addresses = addressService.findAll()
                .stream()
                .map(addressMapper::mapToResponse)
                .toList();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/search")
    ResponseEntity<AddressResponse> findByStreetAddress(@RequestParam @NotBlank String streetAddress) {
        var address = addressService.findByStreetAddress(streetAddress);
        return ResponseEntity.ok(address);
    }
}
