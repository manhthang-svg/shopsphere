package spring.security.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.dto.request.ShopRequest;
import spring.security.dto.response.ShopResponse;
import spring.security.entity.Shop;
import spring.security.entity.Users;
import spring.security.enums.ErrorCode;
import spring.security.enums.ShopStatus;
import spring.security.exceptions.AppException;
import spring.security.mapper.ShopMapper;
import spring.security.repository.ShopRepository;
import spring.security.repository.UserRepository;
import spring.security.service.ShopService;
import spring.security.utils.SecurityUtils;

@Service
public class ShopServiceImpl implements ShopService {
    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    public ShopServiceImpl(SecurityUtils securityUtils, UserRepository userRepository, ShopRepository shopRepository, ShopMapper shopMapper) {
        this.securityUtils = securityUtils;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
        this.shopMapper = shopMapper;
    }

    @Override
    @Transactional
    public ShopResponse registerShop(ShopRequest req){
        String username = securityUtils.getCurrentUsername();
        Users user = userRepository.findByUsername(username)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        Shop newShop = shopMapper.toShopEntity(req);
        boolean isOwnAShop = shopRepository.existsShopByOwner(user);
        if(isOwnAShop) throw new AppException(ErrorCode.ALREADY_OWN_A_SHOP);
        newShop.setOwner(user);
        newShop.setStatus(ShopStatus.PENDING);
        Shop savedShop = shopRepository.save(newShop);

        return shopMapper.toShopResponse(savedShop);
    }
}
