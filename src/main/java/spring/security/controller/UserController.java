package spring.security.controller;

import static spring.security.config.OpenApiConfig.BEARER_AUTH_SCHEME;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.dto.response.ApiResponse;
import spring.security.dto.response.UserProfileResponse;
import spring.security.service.UserService;

@RestController
@RequestMapping("/api/me")
@Tag(name = "User", description = "Current user profile APIs")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Get current user profile",
            description = "Returns the authenticated user's profile and saved addresses.",
            security = @SecurityRequirement(name = BEARER_AUTH_SCHEME)
    )
    @GetMapping
    public ResponseEntity<ApiResponse<UserProfileResponse>> getProfile() {
        return ResponseEntity.ok(
               ApiResponse.success(userService.getProfile())
        );
    }
}
