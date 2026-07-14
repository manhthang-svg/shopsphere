package spring.security.controller;

import static spring.security.config.OpenApiConfig.BEARER_AUTH_SCHEME;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.dto.request.ShopRequest;
import spring.security.dto.response.ApiResponse;
import spring.security.dto.response.ShopResponse;
import spring.security.service.ShopService;

@RestController
@RequestMapping("/api/shops")
@Tag(name = "Shop", description = "Shop registration APIs")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @Operation(summary = "Register shop", security = @SecurityRequirement(name = BEARER_AUTH_SCHEME))
    @PostMapping
    @PreAuthorize("hasAuthority('SHOP_CREATE')")
    public ResponseEntity<ApiResponse<ShopResponse>> registerShop(@Valid @RequestBody ShopRequest request) {
        return ResponseEntity.ok(ApiResponse.success(shopService.registerShop(request)));
    }
}
