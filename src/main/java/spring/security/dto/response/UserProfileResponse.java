package spring.security.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import spring.security.entity.Address;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Builder
@Data
public class UserProfileResponse {
    private Long id;
    private String username;
    private Instant createdAt;
    private List<AddressResponse> addresses;
    private ShopResponse shops;
}
