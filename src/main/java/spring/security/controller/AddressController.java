package spring.security.controller;

import static spring.security.config.OpenApiConfig.BEARER_AUTH_SCHEME;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.dto.request.AddressRequest;
import spring.security.dto.response.AddressResponse;
import spring.security.dto.response.ApiResponse;
import spring.security.service.AddressService;

@RestController
@RequestMapping("/api/me/address")
@Tag(name = "Address", description = "Current user address APIs")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(
            summary = "Add new address",
            description = "Creates a new saved address for the authenticated user.",
            security = @SecurityRequirement(name = BEARER_AUTH_SCHEME)
    )
    @PostMapping
    public ResponseEntity<ApiResponse<AddressResponse>> addNewAddress(@Valid @RequestBody AddressRequest addressRequest) {
        return ResponseEntity.ok(
                ApiResponse.success(addressService.addNewAddress(addressRequest))
        );
    }
}
