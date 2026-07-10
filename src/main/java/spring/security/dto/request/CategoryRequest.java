package spring.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import spring.security.enums.EntityStatus;

@Data
public class CategoryRequest {
    @NotBlank(message = "Category name must not be blank")
    @Size(max = 100, message = "Category name must not exceed 100 characters")
    private String name;

    @Size(max = 255, message = "Category description must not exceed 255 characters")
    private String description;

    private EntityStatus status = EntityStatus.ACTIVE;

    private Long parentCategoryId;
}
