package spring.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.security.dto.response.AddressResponse;
import spring.security.dto.response.UserProfileResponse;
import spring.security.entity.Address;
import spring.security.entity.Users;
import spring.security.enums.ErrorCode;
import spring.security.exceptions.AppException;
import spring.security.mapper.AddressMapper;
import spring.security.mapper.UserMapper;
import spring.security.repository.AddressRepository;
import spring.security.repository.UserRepository;
import spring.security.service.UserService;
import spring.security.utils.SecurityUtils;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final SecurityUtils securityUtils;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AddressRepository addressRepository, AddressMapper addressMapper, SecurityUtils securityUtils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.securityUtils = securityUtils;
    }
    @Override
    public UserProfileResponse getProfile(){
        String username = securityUtils.getCurrentUsername();
        log.info("[Get-Profile] getting profile for username = {}",username);

        Users users = userRepository.findByUsername(username).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        UserProfileResponse userResponse = userMapper.toUserProfileResponse(users);
        List<Address> addresses = addressRepository.findByUserId(users.getId());
        List<AddressResponse> addressResponse = addressMapper.toAddressResponseList(addresses);

        userResponse.setAddresses(addressResponse);
        log.info("[Get-Profile] successfully getted");
        return userResponse;
    }
}
