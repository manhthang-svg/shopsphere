package spring.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.security.dto.request.AddressRequest;
import spring.security.dto.response.AddressResponse;
import spring.security.entity.Address;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "default", source = "isDefault", defaultValue = "false")
    Address toAddress(AddressRequest request);
    @Mapping(target = "isDefault", source = "default")
    AddressResponse toAddressResponse(Address address);
    List<AddressResponse> toAddressResponseList(List<Address> addresses);
    @Mapping(target = "default", ignore = true)
    void updateAddress(AddressRequest addressRequest, @MappingTarget Address address);
}
