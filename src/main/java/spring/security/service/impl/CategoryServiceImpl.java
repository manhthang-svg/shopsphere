package spring.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.dto.request.CategoryRequest;
import spring.security.dto.response.CategoryResponse;
import spring.security.entity.Category;
import spring.security.enums.ErrorCode;
import spring.security.exceptions.AppException;
import spring.security.mapper.CategoryMapper;
import spring.security.repository.CategoryRepository;
import spring.security.service.CategoryService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        log.info("[CREATE-CATEGORY] name={}", request.getName());
        if (categoryRepository.existsByNameIgnoreCaseAndDeletedFalse(request.getName())) {
            throw new AppException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }
        Category category = categoryMapper.toCategory(request);
        category.setParentCategory(resolveParentCategory(request.getParentCategoryId()));
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getCategories() {
        return categoryMapper.toCategoryResponseList(categoryRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getCategory(Long id) {
        Category category = findCategory(id);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        log.info("[UPDATE-CATEGORY] id={}, name={}", id, request.getName());
        Category category = findCategory(id);
        if (categoryRepository.existsByNameIgnoreCaseAndIdNotAndDeletedFalse(request.getName(), id)) {
            throw new AppException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }
        categoryMapper.updateCategory(request, category);
        if (request.getParentCategoryId() != null && request.getParentCategoryId().equals(id)) {
            throw new AppException(ErrorCode.CATEGORY_PARENT_INVALID);
        }
        category.setParentCategory(resolveParentCategory(request.getParentCategoryId()));
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        log.info("[DELETE-CATEGORY] id={}", id);
        Category category = findCategory(id);
        categoryRepository.delete(category);
    }

    private Category findCategory(Long id) {
        return categoryRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    private Category resolveParentCategory(Long parentCategoryId) {
        if (parentCategoryId == null) {
            return null;
        }
        return findCategory(parentCategoryId);
    }
}
