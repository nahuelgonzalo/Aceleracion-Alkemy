package com.alkemy.java.service.impl;

import com.alkemy.java.dto.*;
import com.alkemy.java.exceptions.BadRequestException;
import com.alkemy.java.exceptions.NotFoundException;
import com.alkemy.java.mapper.CategoryMapper;
import com.alkemy.java.model.Category;
import com.alkemy.java.repository.CategoryRepository;
import com.alkemy.java.service.AWSS3Service;
import com.alkemy.java.service.CategoryService;
import com.alkemy.java.util.MessageUtil;
import com.alkemy.java.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import static java.util.Locale.getDefault;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private PageUtil pageUtil;

    @Autowired
    private AWSS3Service awss3Service;

    @Override
    public CategoryDetailDTO findDTOById(Long id) {
        return categoryMapper.toDetailDTO(findById(id));
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException(messageUtil.getMessage("exception.notFound.category",
                        new Object[]{id}, getDefault())));
    }

    @Override
    public CategoryDTO addCategory(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRequestDTO.getName().isEmpty()) {
            throw new BadRequestException("Category name cant'be null");
        }
        Category category = categoryMapper.toCategory(categoryRequestDTO);
        category.setImage(awss3Service.setImageBase64(categoryRequestDTO.getImage()));
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public PageDTO<CategoryDTO> findAll(Integer page) {
        if (page <= 0)
            throw new IllegalArgumentException(messageUtil.getMessage("exception.pageDTO",null, getDefault()));
        Page<Category> categoryPage = categoryRepository.findAllByDeletedFalse(pageUtil.pageRequest(page));
        if (categoryPage.getTotalPages() != 0 && page > categoryPage.getTotalPages())
            throw new IllegalArgumentException(messageUtil.getMessage("exception.pageDTO.getTotal", new Object[]{categoryPage.getTotalPages()},getDefault()));
        List<CategoryDTO> categoryDTOList = categoryPage.getContent().stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
        PageDTO<CategoryDTO> pageDTO = new PageDTO<>();
        pageDTO.setTotalPages(categoryPage.getTotalPages());
        pageDTO.setContent(categoryDTOList);
        if (categoryPage.hasPrevious())
            pageDTO.setPreviousUrl(pageUtil.getPreviousUrl(page));
        if (categoryPage.hasNext())
            pageDTO.setNextUrl(pageUtil.getNextUrl(page));
        return pageDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryRequestDTO categoryRequestDTO, Long id) {
        if (categoryRequestDTO.getName().isEmpty())
            throw new BadRequestException("Category name can't be null");
        Category category = categoryRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BadRequestException("Category with id=" + id + " doesn't exists"));
        category.setImage(awss3Service.setImageBase64(categoryRequestDTO.getImage()));
        category.setDescription(categoryRequestDTO.getDescription());
        category.setName(categoryRequestDTO.getName());
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = findById(id);
        category.setDeleteAt(Calendar.getInstance().getTime());
        category.setDeleted(true);
        categoryRepository.save(category);
    }
}
