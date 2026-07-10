package spring.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.dto.request.BrandRequest;
import spring.security.dto.response.BrandResponse;
import spring.security.entity.Brand;
import spring.security.enums.ErrorCode;
import spring.security.exceptions.AppException;
import spring.security.mapper.BrandMapper;
import spring.security.repository.BrandRepository;
import spring.security.service.BrandService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    @Transactional
    public BrandResponse createBrand(BrandRequest request) {
        log.info("[CREATE-BRAND] name={}", request.getName());
        if (brandRepository.existsByNameIgnoreCaseAndDeletedFalse(request.getName())) {
            throw new AppException(ErrorCode.BRAND_ALREADY_EXISTS);
        }
        Brand brand = brandMapper.toBrand(request);
        return brandMapper.toBrandResponse(brandRepository.save(brand));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandResponse> getBrands() {
        return brandMapper.toBrandResponseList(brandRepository.findAll());
    }

    @Override
    @Transactional
    public BrandResponse updateBrand(Long id, BrandRequest request) {
        log.info("[UPDATE-BRAND] id={}, name={}", id, request.getName());
        Brand brand = findBrand(id);
        if (brandRepository.existsByNameIgnoreCaseAndIdNotAndDeletedFalse(request.getName(), id)) {
            throw new AppException(ErrorCode.BRAND_ALREADY_EXISTS);
        }
        brandMapper.updateBrand(request, brand);
        return brandMapper.toBrandResponse(brandRepository.save(brand));
    }

    @Override
    @Transactional
    public void deleteBrand(Long id) {
        log.info("[DELETE-BRAND] id={}", id);
        Brand brand = findBrand(id);
        brandRepository.delete(brand);
    }

    private Brand findBrand(Long id) {
        return brandRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
    }
}
