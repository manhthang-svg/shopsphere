package spring.security.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.dto.request.ShopRequest;
import spring.security.dto.response.ShopResponse;
import spring.security.entity.Roles;
import spring.security.entity.Shop;
import spring.security.entity.Users;
import spring.security.enums.ErrorCode;
import spring.security.enums.ShopStatus;
import spring.security.exceptions.AppException;
import spring.security.mapper.ShopMapper;
import spring.security.repository.RoleRepository;
import spring.security.repository.ShopRepository;
import spring.security.repository.UserRepository;
import spring.security.service.ShopService;
import spring.security.utils.SecurityUtils;

@Service
public class ShopServiceImpl implements ShopService {
    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    public ShopServiceImpl(SecurityUtils securityUtils, UserRepository userRepository, RoleRepository roleRepository, ShopRepository shopRepository, ShopMapper shopMapper) {
        this.securityUtils = securityUtils;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    @Override
    @Transactional
    public ShopResponse approveShop(Long shopId) {
        Shop shop = findPendingShop(shopId);
        Roles sellerRole = roleRepository.findByName("SELLER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        shop.setStatus(ShopStatus.APPROVED);
        Users owner = shop.getOwner();
        owner.getRoles().add(sellerRole);
        userRepository.save(owner);

        return shopMapper.toShopResponse(shopRepository.save(shop));
    }

    @Override
    @Transactional
    public ShopResponse rejectShop(Long shopId) {
        Shop shop = findPendingShop(shopId);
        shop.setStatus(ShopStatus.REJECTED);

        return shopMapper.toShopResponse(shopRepository.save(shop));
    }

    private Shop findPendingShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOP_NOT_FOUND));
        if (shop.getStatus() != ShopStatus.PENDING) {
            throw new AppException(ErrorCode.SHOP_STATUS_INVALID);
        }
        return shop;
    }
}
