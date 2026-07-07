package spring.security.service;

import spring.security.dto.request.AddressRequest;
import spring.security.dto.response.AddressResponse;

public interface AddressService {
    AddressResponse addNewAddress(AddressRequest req);

    void markAddressAsDefault(Long id);

    AddressResponse updateAddress(AddressRequest request, long id);
}
