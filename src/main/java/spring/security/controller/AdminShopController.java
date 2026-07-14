package spring.security.controller;

import static spring.security.config.OpenApiConfig.BEARER_AUTH_SCHEME;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.dto.response.ApiResponse;
import spring.security.dto.response.ShopResponse;
import spring.security.service.ShopService;

@RestController
@RequestMapping("/api/admin/shops")
@Tag(name = "Admin Shop", description = "Admin shop approval APIs")
@RequiredArgsConstructor
public class AdminShopController {
    private final ShopService shopService;

    @Operation(summary = "Approve shop", security = @SecurityRequirement(name = BEARER_AUTH_SCHEME))
    @PostMapping("/{shopId}/approve")
    @PreAuthorize("hasAuthority('SHOP_APPROVE')")
    public ResponseEntity<ApiResponse<ShopResponse>> approveShop(@PathVariable Long shopId) {
        return ResponseEntity.ok(ApiResponse.success(shopService.approveShop(shopId)));
    }

    @Operation(summary = "Reject shop", security = @SecurityRequirement(name = BEARER_AUTH_SCHEME))
    @PostMapping("/{shopId}/reject")
    @PreAuthorize("hasAuthority('SHOP_REJECT')")
    public ResponseEntity<ApiResponse<ShopResponse>> rejectShop(@PathVariable Long shopId) {
        return ResponseEntity.ok(ApiResponse.success(shopService.rejectShop(shopId)));
    }
}
