package spring.security.service;

import spring.security.dto.request.BrandRequest;
import spring.security.dto.response.BrandResponse;

import java.util.List;

public interface BrandService {
    BrandResponse createBrand(BrandRequest request);

    List<BrandResponse> getBrands();

    BrandResponse updateBrand(Long id, BrandRequest request);

    void deleteBrand(Long id);
}
