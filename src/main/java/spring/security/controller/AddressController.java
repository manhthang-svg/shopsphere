package spring.security.controller;

import static spring.security.config.OpenApiConfig.BEARER_AUTH_SCHEME;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @Operation(
            summary = "mark address default",
            description = "set an address to default",
            security = @SecurityRequirement(name = BEARER_AUTH_SCHEME)
    )
    @PutMapping("/{id}/default")
    public ResponseEntity<ApiResponse<AddressResponse>> markDefault(@PathVariable long id) {
        addressService.markAddressAsDefault(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "update address",
            description = "update an address",
            security = @SecurityRequirement(name = BEARER_AUTH_SCHEME)
    )
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponse>> updateAddress(@PathVariable long id,
                                                                      @RequestBody AddressRequest addressRequest) {
        return ResponseEntity.ok(
                ApiResponse.success(addressService.updateAddress(addressRequest,id))
        );
    }
    @Operation(
            summary = "delete address",
            description = "delete an address",
            security = @SecurityRequirement(name = BEARER_AUTH_SCHEME)
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> softDeleteAddress(@PathVariable long id) {
        addressService.softDeleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
