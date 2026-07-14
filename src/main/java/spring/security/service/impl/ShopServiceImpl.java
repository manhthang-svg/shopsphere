package spring.security.service.impl;

import org.springframework.stereotype.Service;
import spring.security.dto.request.ShopRequest;
import spring.security.dto.response.ShopResponse;
import spring.security.dto.response.UserResponse;
import spring.security.entity.Shop;
import spring.security.entity.Users;
import spring.security.enums.ErrorCode;
import spring.security.exceptions.AppException;
import spring.security.mapper.ShopMapper;
import spring.security.mapper.UserMapper;
import spring.security.repository.RoleRepository;
import spring.security.repository.ShopRepository;
import spring.security.repository.UserRepository;
import spring.security.utils.SecurityUtils;

@Service
public class ShopServiceImpl implements spring.security.service.ShopService {
    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;
    public ShopServiceImpl(SecurityUtils securityUtils, UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, ShopRepository shopRepository, ShopMapper shopMapper) {
        this.securityUtils = securityUtils;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.shopRepository = shopRepository;
        this.shopMapper = shopMapper;
    }
    public ShopResponse registerShop(ShopRequest req){
        String username = securityUtils.getCurrentUsername();
        Users user = userRepository.findByUsername(username)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        Shop newShop = shopMapper.toShopEntity(req);

        newShop.setOwner(user);
        Shop savedShop = shopRepository.save(newShop);
            return shopMapper.toShopResponse(savedShop);
    }
}
