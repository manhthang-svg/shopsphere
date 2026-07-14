package spring.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopRequest {
    @NotBlank(message = "Tên cửa hàng không được để trống")
    @Size(max = 255, message = "Tên cửa hàng không được vượt quá 255 ký tự")
    private String name;

    private String description;

    private String logoUrl;

}
