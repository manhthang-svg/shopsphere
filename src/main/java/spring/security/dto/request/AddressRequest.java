package spring.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressRequest {

    @NotBlank(message = "Recipient name must not be blank")
    @Size(max = 100, message = "Recipient name must not exceed 100 characters")
    private String recipientName;

    @NotBlank(message = "Phone number must not be blank")
    @Pattern(
            regexp = "^(0|\\+84)[0-9]{9,10}$",
            message = "Invalid Vietnamese phone number"
    )
    private String phone;

    @NotBlank(message = "Province must not be blank")
    @Size(max = 50, message = "Province must not exceed 50 characters")
    private String province;

    @NotBlank(message = "District must not be blank")
    @Size(max = 50, message = "District must not exceed 50 characters")
    private String district;

    @NotBlank(message = "Ward must not be blank")
    @Size(max = 50, message = "Ward must not exceed 50 characters")
    private String ward;

    @NotBlank(message = "Detail address must not be blank")
    @Size(max = 500, message = "Detail address must not exceed 500 characters")
    private String detailAddress;

    private Boolean isDefault;
}
