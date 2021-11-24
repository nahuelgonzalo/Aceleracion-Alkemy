package com.alkemy.java.mapper;

import com.alkemy.java.dto.NewRequestDTO;
import com.alkemy.java.dto.NewResponseDTO;
import com.alkemy.java.model.News;
import com.alkemy.java.service.CategoryService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, CategoryService.class})
public interface NewMapper {
    List<NewResponseDTO> toNewDTOList(List<News> news);
    @Mapping(target = "category", source = "category")
    NewResponseDTO toDto(News news);
    @Mapping(target = "category", source = "idCategory")
    News toNews(NewRequestDTO newDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", source = "idCategory")
    void updateFromDTO(NewRequestDTO newDTO, @MappingTarget News news);
}
