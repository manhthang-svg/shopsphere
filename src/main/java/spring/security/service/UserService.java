package spring.security.service;

import spring.security.dto.response.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile();
}
