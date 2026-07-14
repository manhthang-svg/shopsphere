package spring.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.security.entity.Users;
import spring.security.enums.ShopStatus;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse {
    private Long id;
    private String name;
    private String description;
    private String logoUrl;
    private ShopStatus status;
    private String ownerName;
}
