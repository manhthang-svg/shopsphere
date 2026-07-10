package spring.security.controller;

import static spring.security.config.OpenApiConfig.BEARER_AUTH_SCHEME;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.dto.request.BrandRequest;
import spring.security.dto.response.ApiResponse;
import spring.security.dto.response.BrandResponse;
import spring.security.service.BrandService;

@RestController
@RequestMapping({"/api/admin/brands", "/admin/brands"})
@Tag(name = "Admin Brand", description = "Admin brand management APIs")
@RequiredArgsConstructor
public class AdminBrandController {
    private final BrandService brandService;

    @Operation(summary = "Create brand", security = @SecurityRequirement(name = BEARER_AUTH_SCHEME))
    @PostMapping
    @PreAuthorize("hasAuthority('BRAND_CREATE')")
    public ResponseEntity<ApiResponse<BrandResponse>> createBrand(@Valid @RequestBody BrandRequest request) {
        return ResponseEntity.ok(ApiResponse.success(brandService.createBrand(request)));
    }

    @Operation(summary = "Update brand", security = @SecurityRequirement(name = BEARER_AUTH_SCHEME))
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('BRAND_UPDATE')")
    public ResponseEntity<ApiResponse<BrandResponse>> updateBrand(@PathVariable Long id,
                                                                  @Valid @RequestBody BrandRequest request) {
        return ResponseEntity.ok(ApiResponse.success(brandService.updateBrand(id, request)));
    }

    @Operation(summary = "Delete brand", security = @SecurityRequirement(name = BEARER_AUTH_SCHEME))
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('BRAND_DELETE')")
    public ResponseEntity<ApiResponse<?>> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
