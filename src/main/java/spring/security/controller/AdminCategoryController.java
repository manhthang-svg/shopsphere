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
import spring.security.dto.request.CategoryRequest;
import spring.security.dto.response.ApiResponse;
import spring.security.dto.response.CategoryResponse;
import spring.security.service.CategoryService;

@RestController
@RequestMapping({"/api/admin/categories", "/admin/categories"})
@Tag(name = "Admin Category", description = "Admin category management APIs")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Create category", security = @SecurityRequirement(name = BEARER_AUTH_SCHEME))
    @PostMapping
    @PreAuthorize("hasAuthority('CATEGORY_CREATE')")
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.createCategory(request)));
    }

    @Operation(summary = "Update category", security = @SecurityRequirement(name = BEARER_AUTH_SCHEME))
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CATEGORY_UPDATE')")

    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable Long id,
                                                                        @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.updateCategory(id, request)));
    }

    @Operation(summary = "Delete category", security = @SecurityRequirement(name = BEARER_AUTH_SCHEME))
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CATEGORY_DELETE')")

    public ResponseEntity<ApiResponse<?>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
