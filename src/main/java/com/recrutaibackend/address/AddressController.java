package com.recrutaibackend.address;

import com.recrutaibackend.auth.user.UserService;
import com.recrutaibackend.institution.InstitutionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final UserService userService;
    private final InstitutionService institutionService;


    public AddressController(UserService userService, InstitutionService institutionService) {
        this.userService = userService;
        this.institutionService = institutionService;
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<Void> setUserAddress(@RequestBody @Valid AddressRequest request, @PathVariable Long id) {
        userService.setAddress(request, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/institution/{id}")
    public ResponseEntity<Void> setUserInstitution(@RequestBody @Valid AddressRequest request, @PathVariable Long id) {
        institutionService.setAddress(request, id);
        return ResponseEntity.noContent().build();
    }
}
