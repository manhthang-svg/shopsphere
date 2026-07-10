package spring.security.dto.response;

import lombok.Builder;
import lombok.Data;
import spring.security.enums.EntityStatus;

@Data
@Builder
public class CategorySummaryResponse {
    private Long id;
    private String name;
    private EntityStatus status;
}
