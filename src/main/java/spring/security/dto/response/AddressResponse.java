package spring.security.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AddressResponse {
    private Long id;
    private String recipientName;
    private String phone;
    private String province;
    private String district;
    private String ward;
    private String detailAddress;

    // Tự động map sang biến "isDefault" trong JSON (hoặc is_default tùy cấu hình Jackson của bạn)
    private boolean isDefault;
}
