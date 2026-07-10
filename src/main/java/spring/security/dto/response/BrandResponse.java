package spring.security.dto.response;

import lombok.Builder;
import lombok.Data;
import spring.security.enums.EntityStatus;

@Data
@Builder
public class BrandResponse {
    private Long id;
    private String name;
    private String logo;
    private String description;
    private EntityStatus status;
}
