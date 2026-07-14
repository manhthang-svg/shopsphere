package spring.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.security.dto.request.ShopRequest;
import spring.security.dto.response.ShopResponse;
import spring.security.entity.Shop;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    ShopResponse toShopResponse(Shop shop);
    @Mapping(target = "owner",ignore = true)
    @Mapping(target = "status",ignore = true)
    Shop toShopEntity(ShopRequest shopRequest);
}
