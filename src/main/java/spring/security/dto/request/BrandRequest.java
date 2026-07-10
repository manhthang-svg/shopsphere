package spring.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import spring.security.enums.EntityStatus;

@Data
public class BrandRequest {
    @NotBlank(message = "Brand name must not be blank")
    @Size(max = 100, message = "Brand name must not exceed 100 characters")
    private String name;

    @Size(max = 255, message = "Brand description must not exceed 255 characters")
    private String description;

    @Size(max = 500, message = "Brand logo must not exceed 500 characters")
    private String logo;

    private EntityStatus status = EntityStatus.ACTIVE;
}
