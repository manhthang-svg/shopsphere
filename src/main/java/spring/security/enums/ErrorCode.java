package spring.security.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("USER_001", "User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("USER_002", "Username or email already exists", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND("USER_003", "Role not found", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD("AUTH_002", "Invalid password", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("AUTH_001", "Unauthorized or invalid token", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED("AUTH_003", "Access denied", HttpStatus.FORBIDDEN),
    REFRESTOKEN_EXPIRED("AUTH_004", "Refresh token expired", HttpStatus.UNAUTHORIZED),
    REFRESHTOKEN_NOT_FOUND("AUTH_005", "Refresh token not found", HttpStatus.UNAUTHORIZED),
    INVALID_REFRESH_TOKEN("AUTH_006", "Invalid refresh token", HttpStatus.UNAUTHORIZED),
    ADDRESS_NOT_FOUND("ADDRESS_001", "Address not found", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND("CATEGORY_001", "Category not found", HttpStatus.NOT_FOUND),
    CATEGORY_ALREADY_EXISTS("CATEGORY_002", "Category already exists", HttpStatus.BAD_REQUEST),
    CATEGORY_PARENT_INVALID("CATEGORY_003", "Category parent is invalid", HttpStatus.BAD_REQUEST),
    BRAND_NOT_FOUND("BRAND_001", "Brand not found", HttpStatus.NOT_FOUND),
    BRAND_ALREADY_EXISTS("BRAND_002", "Brand already exists", HttpStatus.BAD_REQUEST),
    ALREADY_OWN_A_SHOP("SHOP_001", "You already own a shop", HttpStatus.BAD_REQUEST),
    SHOP_NOT_FOUND("SHOP_002", "Shop not found", HttpStatus.NOT_FOUND),
    SHOP_STATUS_INVALID("SHOP_003", "Shop status is invalid for this action", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION("SYS_999", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
