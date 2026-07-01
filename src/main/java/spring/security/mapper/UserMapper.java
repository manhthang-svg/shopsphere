package spring.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.security.dto.response.UserProfileResponse;
import spring.security.dto.response.UserResponse;
import spring.security.entity.Users;

@Mapper(componentModel = "spring",uses = RoleMapper.class)
public interface UserMapper {
    UserResponse toUserResponse(Users users);
    @Mapping(target = "addresses", ignore = true)
    UserProfileResponse toUserProfileResponse(Users users);
}
