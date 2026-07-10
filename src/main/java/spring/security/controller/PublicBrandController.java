package spring.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.dto.response.ApiResponse;
import spring.security.dto.response.BrandResponse;
import spring.security.service.BrandService;

import java.util.List;

@RestController
@RequestMapping({"/api/brands", "/brands"})
@Tag(name = "Brand", description = "Public brand APIs")
@RequiredArgsConstructor
public class PublicBrandController {
    private final BrandService brandService;

    @Operation(summary = "Get brands")
    @GetMapping
    public ResponseEntity<ApiResponse<List<BrandResponse>>> getBrands() {
        return ResponseEntity.ok(ApiResponse.success(brandService.getBrands()));
    }
}
