package spring.security.service;

import spring.security.dto.request.ShopRequest;
import spring.security.dto.response.ShopResponse;

public interface ShopService {
    ShopResponse registerShop(ShopRequest req);
}
