package spring.security.dto.response;

import lombok.Builder;
import lombok.Data;
import spring.security.enums.EntityStatus;

@Data
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private EntityStatus status;
    private CategorySummaryResponse parentCategory;
}
