package com.recrutaibackend.address;

import com.recrutaibackend.auth.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final UserService userService;


    public AddressController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> setUserAddress(@RequestBody @Valid AddressRequest request, @PathVariable Long id) {
        userService.setAddress(request, id);
        return ResponseEntity.noContent().build();
    }
}
