package spring.security.mapper;

import org.mapstruct.Mapper;
import spring.security.dto.response.AddressResponse;
import spring.security.entity.Address;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponse toAddressResponse(Address address);
    List<AddressResponse> toAddressResponseList(List<Address> addresses);
}
