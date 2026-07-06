package spring.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.security.dto.request.AddressRequest;
import spring.security.dto.response.AddressResponse;
import spring.security.entity.Address;
import spring.security.entity.Users;
import spring.security.enums.ErrorCode;
import spring.security.exceptions.AppException;
import spring.security.mapper.AddressMapper;
import spring.security.repository.AddressRepository;
import spring.security.repository.UserRepository;
import spring.security.service.AddressService;
import spring.security.utils.SecurityUtils;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    public AddressServiceImpl(SecurityUtils securityUtils, UserRepository userRepository, AddressMapper addressMapper, AddressRepository addressRepository) {
        this.securityUtils = securityUtils;
        this.userRepository = userRepository;
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }
    @Override
    public AddressResponse addNewAddress(AddressRequest req){
        String username = securityUtils.getCurrentUsername();
        log.info("[ADD-ADDRESS] adding address = {} for user = {}",req.getRecipientName(),username);
        Users users = userRepository.findByUsername(username).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        Address newAddress = addressMapper.toAddress(req);
        newAddress.setUser(users);
        Address savedAddress = addressRepository.save(newAddress);
        log.info("[ADD-ADDRESS] successfully added");
        return addressMapper.toAddressResponse(savedAddress);
    }
}
